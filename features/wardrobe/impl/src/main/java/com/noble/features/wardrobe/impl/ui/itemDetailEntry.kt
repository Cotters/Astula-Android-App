package com.noble.features.wardrobe.impl.ui

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.noble.features.wardrobe.api.ItemDetailScreen
import com.noble.features.wardrobe.api.WardrobeScreen
import com.noble.features.wardrobe.impl.WardrobeItemDetailsScreen
import com.noble.features.wardrobe.impl.WardrobeScreen

fun EntryProviderScope<NavKey>.wardrobeEntry(backStack: NavBackStack<NavKey>) {
    entry<WardrobeScreen> {
        WardrobeScreen(onItemTapped = {
            backStack.add(ItemDetailScreen(itemId = it))
        })
    }
}

fun EntryProviderScope<NavKey>.itemDetailEntry() {
    entry<ItemDetailScreen> { item ->
        WardrobeItemDetailsScreen(itemId = item.itemId)
    }
}
