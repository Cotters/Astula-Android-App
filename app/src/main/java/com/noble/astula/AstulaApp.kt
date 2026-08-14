package com.noble.astula

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import com.noble.features.wardrobe.WardrobeScreen
import com.noble.features.wardrobe.WardrobeViewModel
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppScreen: NavKey {
    @Serializable
    data object Wardrobe : AppScreen
    @Serializable
    data class ItemDetail(val itemId: Int) : AppScreen
    @Serializable
    data object Account: AppScreen
}

@Composable
fun AstulaApp(
    wardrobeViewModel: WardrobeViewModel,
) {
//    val backStack = rememberNavBackStack(AppScreen.Wardrobe)
    val backstack = remember { mutableStateListOf<AppScreen>(AppScreen.Wardrobe) }
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }
    val viewState by wardrobeViewModel.viewState.collectAsStateWithLifecycle()

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
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = {
                        currentDestination = it
                        backstack.clear()
                        backstack.addAll(setOf(AppScreen.Wardrobe, it.toNavKey()))
                    }
                )
            }
        }
    ) {
        NavDisplay(
            backStack = backstack,
            onBack = { backstack.removeLastOrNull() },
        ) { key ->
            when (key) {
                AppScreen.Wardrobe -> {
                    NavEntry(key) {
                        WardrobeScreen(viewState)
                    }
                }

                is AppScreen.ItemDetail -> NavEntry(key) {
                    Text("Item Detail view come soon...")
                }

                AppScreen.Account -> NavEntry(key) {
                    Text("Account Screen coming soon...")
                }

            }
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: Int,
) {
    HOME("Home", R.drawable.ic_home),
    FAVORITES("Favorites", R.drawable.ic_favorite),
    PROFILE("Profile", R.drawable.ic_account_box), ;

    fun toNavKey(): AppScreen {
        return when (this) {
            HOME -> AppScreen.Wardrobe
            FAVORITES -> AppScreen.ItemDetail(itemId = 123)
            PROFILE -> AppScreen.Account
        }
    }
}