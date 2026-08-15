package com.noble.features.upload.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
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
)

@HiltViewModel
class UploadViewModel @Inject constructor() : ViewModel() {

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
        if (isSaving()) return
        _state.update { it.copy(isSaving = true) }
    }

    private fun isSaving(): Boolean = _state.value.isSaving

}