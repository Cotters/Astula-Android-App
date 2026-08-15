package com.noble.astula

import androidx.annotation.DrawableRes
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Icon
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.noble.account.api.AccountScreen
import com.noble.account.impl.accountEntry
import com.noble.features.upload.api.UploadScreen
import com.noble.features.upload.impl.uploadScreenEntry
import com.noble.features.wardrobe.api.WardrobeScreen
import com.noble.features.wardrobe.impl.ui.itemDetailEntry
import com.noble.features.wardrobe.impl.ui.wardrobeEntry
import com.noble.presentation.rememberListDetailSceneStrategy

data class NavigationBarItem(
    val key: NavKey,
    @DrawableRes val icon: Int,
    val label: String,
)

@Composable
fun AstulaApp() {
    val navigationBarItems = listOf(
        NavigationBarItem(WardrobeScreen, R.drawable.ic_home, "Home"),
        NavigationBarItem(UploadScreen, R.drawable.ic_add, "Upload Photo"),
        NavigationBarItem(AccountScreen, R.drawable.ic_account_box, "Account"),
    )
    val topLevelDestinations = navigationBarItems.map(NavigationBarItem::key)
    val backStack = rememberNavBackStack(WardrobeScreen)

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            navigationBarItems.forEach { destination ->
                item(
                    icon = {
                        Icon(
                            painterResource(destination.icon),
                            contentDescription = destination.label,
                        )
                    },
                    selected = destination.key == backStack.lastOrNull { it in topLevelDestinations },
                    onClick = {
                        backStack.clear()
                        backStack.addAll(listOf(WardrobeScreen, destination.key))
                    },
                )
            }
        }
    ) {
        NavDisplay(
            backStack = backStack,
            onBack = backStack::removeLastOrNull,
            sceneStrategies = listOf(rememberListDetailSceneStrategy()),
            entryProvider = entryProvider {
                wardrobeEntry(backStack)
                itemDetailEntry()
                uploadScreenEntry()
                accountEntry()
            },
            transitionSpec = {
                slideInHorizontally(
                    initialOffsetX = { it }
                ) togetherWith slideOutHorizontally(targetOffsetX = { -it })
            },
            popTransitionSpec = {
                slideInHorizontally(
                    initialOffsetX = { -it }
                ) togetherWith slideOutHorizontally(targetOffsetX = { it })
            },
            predictivePopTransitionSpec = {
                slideInHorizontally(
                    initialOffsetX = { -it }
                ) togetherWith slideOutHorizontally(targetOffsetX = { it })
            }
        )
    }
}

