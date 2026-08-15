package com.noble.features.wardrobe.api

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object WardrobeScreen : NavKey

@Serializable
data class ItemDetailScreen(val itemId: Int) : NavKey
