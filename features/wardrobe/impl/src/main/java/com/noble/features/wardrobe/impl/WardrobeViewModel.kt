package com.noble.features.wardrobe.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

data class ClothingItem(
    val id: Int,
    val name: String,
    val description: String = ""
)

// Domain Model
sealed interface WardrobeState {
    data object Loading : WardrobeState
    data class Loaded(val items: List<ClothingItem>) : WardrobeState
    data class Error(val message: String) : WardrobeState
}

class WardrobeViewModel : ViewModel() {

    private val _state = MutableStateFlow<WardrobeState>(WardrobeState.Loading)
    val viewState: StateFlow<WardrobeState> = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
            initialValue = WardrobeState.Loading,
        )

    init {
        viewModelScope.launch {
            try {
                delay(2.seconds)
//                throw Exception()
                _state.emit(
                    WardrobeState.Loaded(
                        listOf(
                            ClothingItem(id = 1, name = "Red Shoes"),
                            ClothingItem(id = 2, name = "Blue Shirt"),
                            ClothingItem(id = 3, name = "Socks"),
                            ClothingItem(id = 4, name = "Jeans"),
                        )
                    )
                )
            } catch (e: Exception) {
                _state.emit(WardrobeState.Error(message = e.message ?: "Error retrieving clothes."))
            }
        }
    }
}