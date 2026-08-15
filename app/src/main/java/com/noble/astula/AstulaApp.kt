package com.noble.astula

import androidx.compose.material3.Icon
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.noble.account.impl.accountEntry
import com.noble.astula.navigation.AppDestinations
import com.noble.astula.navigation.AppScreen
import com.noble.features.wardrobe.api.WardrobeScreen
import com.noble.features.wardrobe.impl.ui.itemDetailEntry
import com.noble.features.wardrobe.impl.ui.wardrobeEntry


@Composable
fun AstulaApp() {
    val backStack = rememberNavBackStack(WardrobeScreen)
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            painterResource(it.icon),
                            contentDescription = it.label,
                        )
                    },
                    selected = it == currentDestination,
                    onClick = {
                        currentDestination = it
                        backStack.clear()
                        backStack.addAll(setOf(WardrobeScreen, it.toNavKey()))
                    }
                )
            }
        }
    ) {
        NavDisplay(
            backStack = backStack,
            onBack = {
                backStack.removeLastOrNull()
                if (backStack.last() is WardrobeScreen) {
                    currentDestination = AppDestinations.HOME
                }
            },
            entryProvider = entryProvider {
                wardrobeEntry(backStack)
                itemDetailEntry()
                uploadEntry()
                accountEntry()
            }
        )
    }
}

private fun EntryProviderScope<NavKey>.uploadEntry() {
    entry<AppScreen.Upload> {
        UploadScreen()
    }
}
