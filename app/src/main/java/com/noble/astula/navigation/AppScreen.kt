package com.noble.astula.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppScreen : NavKey {
    @Serializable
    data object Wardrobe : AppScreen {
        @Serializable
        data class ItemDetail(val itemId: Int) : AppScreen
    }

    @Serializable
    data object Upload : AppScreen

    @Serializable
    data object Account : AppScreen
}
