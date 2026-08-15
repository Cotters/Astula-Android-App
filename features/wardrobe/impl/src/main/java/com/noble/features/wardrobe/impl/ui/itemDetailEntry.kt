package com.noble.features.wardrobe.impl.ui

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import com.noble.features.wardrobe.api.ItemDetailScreen
import com.noble.features.wardrobe.api.WardrobeScreen
import com.noble.features.wardrobe.impl.WardrobeItemDetailsScreen
import com.noble.features.wardrobe.impl.WardrobeScreen
import com.noble.features.wardrobe.impl.WardrobeState
import com.noble.features.wardrobe.impl.WardrobeViewModel

private const val navDurationMillis = 200

fun EntryProviderScope<NavKey>.wardrobeEntry(backStack: NavBackStack<NavKey>) {
    entry<WardrobeScreen>(
        metadata = NavDisplay.transitionSpec {
            fadeIn(tween(navDurationMillis)) togetherWith fadeOut(tween(navDurationMillis))
        } + NavDisplay.popTransitionSpec {
            EnterTransition.None togetherWith fadeOut(tween(navDurationMillis))
        } + NavDisplay.predictivePopTransitionSpec {
            EnterTransition.None togetherWith fadeOut(tween(navDurationMillis))
        }
    ) {
        val viewModel = hiltViewModel<WardrobeViewModel>()
        val viewState: WardrobeState by viewModel.viewState.collectAsStateWithLifecycle()
        WardrobeScreen(
            viewState = viewState,
            onItemTapped = {
                backStack.add(ItemDetailScreen(itemId = it))
            }
        )
    }
}

fun EntryProviderScope<NavKey>.itemDetailEntry() {
    entry<ItemDetailScreen> { item ->
        WardrobeItemDetailsScreen(itemId = item.itemId)
    }
}
