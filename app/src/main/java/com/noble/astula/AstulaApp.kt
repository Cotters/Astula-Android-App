package com.noble.astula

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

@Composable
fun AstulaApp() {
    val bottomNavDestinations = setOf(WardrobeScreen, UploadScreen, AccountScreen)
    val navBarIcons: Map<NavKey, Int> = mapOf(
        WardrobeScreen to R.drawable.ic_home,
        UploadScreen to R.drawable.ic_add,
        AccountScreen to R.drawable.ic_account_box,
    )
    val backStack = rememberNavBackStack(WardrobeScreen)
    val strategy = rememberListDetailSceneStrategy<NavKey>()

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            bottomNavDestinations.forEach { destination ->
                item(
                    icon = {
                        Icon(
                            painterResource(navBarIcons.getValue(destination)),
                            contentDescription = destination.toString(),
                        )
                    },
                    selected = destination == backStack.lastOrNull { it in bottomNavDestinations },
                    onClick = {
                        backStack.clear()
                        backStack.addAll(listOf(WardrobeScreen, destination))
                    },
                )
            }
        }
    ) {
        NavDisplay(
            backStack = backStack,
            onBack = backStack::removeLastOrNull,
            sceneStrategies = listOf(strategy),
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

