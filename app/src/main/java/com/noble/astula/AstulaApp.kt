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
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.noble.account.impl.accountEntry
import com.noble.astula.navigation.AppDestinations
import com.noble.astula.navigation.AppScreen
import com.noble.features.wardrobe.WardrobeItemDetailsScreen
import com.noble.features.wardrobe.WardrobeScreen


@Composable
fun AstulaApp() {
    val backStack = rememberNavBackStack(AppScreen.Wardrobe)
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
                        backStack.addAll(setOf(AppScreen.Wardrobe, it.toNavKey()))
                    }
                )
            }
        }
    ) {
        NavDisplay(
            backStack = backStack,
            onBack = {
                backStack.removeLastOrNull()
                if (backStack.last() is AppScreen.Wardrobe) {
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

private fun EntryProviderScope<NavKey>.itemDetailEntry() {
    entry<AppScreen.Wardrobe.ItemDetail> { item ->
        WardrobeItemDetailsScreen(itemId = item.itemId)
    }
}

private fun EntryProviderScope<NavKey>.wardrobeEntry(backStack: NavBackStack<NavKey>) {
    entry<AppScreen.Wardrobe> {
        WardrobeScreen(onItemTapped = {
            backStack.add(AppScreen.Wardrobe.ItemDetail(itemId = it))
        })
    }
}
