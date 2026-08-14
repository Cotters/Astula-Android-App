package com.noble.astula

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.noble.astula.navigation.AppDestinations
import com.noble.astula.navigation.AppScreen
import com.noble.features.account.AccountScreen
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
        ) { key ->
            when (key) {
                AppScreen.Wardrobe -> NavEntry(key) {
                    WardrobeScreen(onItemTapped = {
                        backStack.add(AppScreen.Wardrobe.ItemDetail(itemId = it))
                    })
                }

                is AppScreen.Wardrobe.ItemDetail -> NavEntry(key) {
                    WardrobeItemDetailsScreen(itemId = key.itemId)
                }

                AppScreen.Upload -> NavEntry(key) {
                    UploadScreen()
                }

                AppScreen.Account -> NavEntry(key) {
                    AccountScreen()
                }

                else -> NavEntry(key) {
                    Text("Route not found...")
                }
            }
        }
    }
}
