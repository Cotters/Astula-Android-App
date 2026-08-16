package com.noble.features.upload.impl

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test


class UploadScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun submitBecomesEnabledWhenRequiredFieldsArePopulated() {
        var name by mutableStateOf("")
        var description by mutableStateOf("")
        var didSubmit = false
        composeTestRule.setContent {
            UploadForm(
                viewState = UploadViewState(name = name, description = description),
                onNameChanged = { name = it },
                onDescriptionChanged = { description = it },
                onSubmitPressed = { didSubmit = true },
            )
        }
        composeTestRule
            .onNodeWithText("Submit")
            .assertIsNotEnabled()

        enterName()

        composeTestRule
            .onNodeWithText("Submit")
            .assertIsNotEnabled()

        enterDescription()

        composeTestRule
            .onNodeWithText("Submit")
            .assertIsEnabled()
            .performClick()

        assert(didSubmit)
    }

    @Test
    fun submitBecomesDisabledWhileSaving() {
        var name by mutableStateOf("")
        var description by mutableStateOf("")
        var didSubmit by mutableStateOf(false)
        composeTestRule.setContent {
            UploadForm(
                viewState = UploadViewState(name = name, description = description, isSaving = didSubmit),
                onNameChanged = { name = it },
                onDescriptionChanged = { description = it },
                onSubmitPressed = { didSubmit = true },
            )
        }

        enterName()
        enterDescription()

        composeTestRule
            .onNode(hasClickAction() and hasText("Submit"))
            .performClick()

        composeTestRule
            .onNode(hasClickAction() and hasStateDescription("Saving"))
            .assertIsNotEnabled()

        assert(didSubmit)
    }

    private fun enterName(text: String = "Test Name") {
        composeTestRule
            .onNode(hasText("Name") and hasSetTextAction())
            .performTextInput(text)
    }

    private fun enterDescription(text: String = "Test Description") {
        composeTestRule
            .onNode(hasText("Description") and hasSetTextAction())
            .performTextInput(text)
    }

    fun hasStateDescription(text: String) =
        SemanticsMatcher("StateDescription contains '$text'") {
            it.config
                .getOrNull(SemanticsProperties.StateDescription)
                ?.contains(text) == true
        }
}