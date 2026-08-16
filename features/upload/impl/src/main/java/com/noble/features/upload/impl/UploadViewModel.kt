package com.noble.features.upload.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noble.features.upload.api.domain.NewWardrobeItem
import com.noble.features.upload.impl.domain.SaveWardrobeItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface UploadViewEvent {
    data class NameUpdated(val value: String) : UploadViewEvent
    data class DescriptionUpdated(val value: String) : UploadViewEvent
    data object SubmitPressed : UploadViewEvent
}

data class UploadViewState(
    val name: String = "",
    val description: String = "",
    val isSaving: Boolean = false,
) {
    val canSubmit: Boolean
        get() = name.isNotEmpty() && description.isNotEmpty() && !isSaving
}

@HiltViewModel
class UploadViewModel @Inject constructor(
    private val saveWardrobeItemUseCase: SaveWardrobeItemUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(UploadViewState())
    val viewState: StateFlow<UploadViewState> = _state

    fun onViewEvent(event: UploadViewEvent) {
        when (event) {
            is UploadViewEvent.NameUpdated -> onNameUpdated(event.value)
            is UploadViewEvent.DescriptionUpdated -> onDescriptionUpdated(event.value)
            UploadViewEvent.SubmitPressed -> onSubmitPressed()
        }
    }

    private fun onNameUpdated(newValue: String) {
        if (isSaving()) return
        _state.update { it.copy(name = newValue) }
    }

    private fun onDescriptionUpdated(newValue: String) {
        if (isSaving()) return
        _state.update { it.copy(description = newValue) }
    }

    private fun onSubmitPressed() {
        val state = _state.value
        if (state.canSubmit) return
        _state.update { it.copy(isSaving = true) }
        viewModelScope.launch {
            saveWardrobeItemUseCase.run(NewWardrobeItem(name = state.name, description = state.description))
        }
    }

    private fun isSaving(): Boolean = _state.value.isSaving

}