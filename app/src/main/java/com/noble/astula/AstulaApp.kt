package com.noble.astula

import androidx.compose.material3.Icon
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.noble.account.impl.accountEntry
import com.noble.astula.navigation.BottomNavDestinations
import com.noble.features.upload.impl.uploadScreenEntry
import com.noble.features.wardrobe.api.WardrobeScreen
import com.noble.features.wardrobe.impl.ui.itemDetailEntry
import com.noble.features.wardrobe.impl.ui.wardrobeEntry


@Composable
fun AstulaApp() {
    val backStack = rememberNavBackStack(WardrobeScreen)
    var currentDestination by rememberSaveable { mutableStateOf(BottomNavDestinations.HOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            currentDestination = bottomNavDestinations(
                currentDestination,
                onItemSelected = { destination ->
                    currentDestination = destination
                    backStack.clear()
                    backStack.addAll(setOf(WardrobeScreen, destination.toNavKey()))
                }
            )
        }
    ) {
        NavDisplay(
            backStack = backStack,
            onBack = {
                backStack.removeLastOrNull()
                if (backStack.last() is WardrobeScreen) {
                    currentDestination = BottomNavDestinations.HOME
                }
            },
            entryProvider = entryProvider {
                wardrobeEntry(backStack)
                itemDetailEntry()
                uploadScreenEntry()
                accountEntry()
            }
        )
    }
}

private fun NavigationSuiteScope.bottomNavDestinations(
    currentDestination: BottomNavDestinations,
    onItemSelected: (BottomNavDestinations) -> Unit,
): BottomNavDestinations {
    BottomNavDestinations.entries.forEach { destination ->
        item(
            icon = {
                Icon(
                    painterResource(destination.icon),
                    contentDescription = destination.label,
                )
            },
            selected = destination == currentDestination,
            onClick = { onItemSelected(destination) },
        )
    }
    return currentDestination
}