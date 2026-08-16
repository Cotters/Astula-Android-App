package com.noble.features.upload.impl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UploadScreen(
    viewState: UploadViewState,
    onViewEvent: (UploadViewEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
    ) { paddingValues ->
        val modifier = Modifier.padding(paddingValues)
        UploadForm(
            viewState = viewState,
            onNameChanged = { onViewEvent(UploadViewEvent.NameUpdated(it)) },
            onDescriptionChanged = { onViewEvent(UploadViewEvent.DescriptionUpdated(it)) },
            onSubmitPressed = { onViewEvent(UploadViewEvent.SubmitPressed) },
            modifier = modifier,
        )
    }
}

@Composable
fun UploadForm(
    viewState: UploadViewState,
    onNameChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    onSubmitPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val widthModifier = Modifier
        .widthIn(max = 500.dp)
        .fillMaxWidth()
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        // TODO Replace with item image.
        Box(
            modifier = widthModifier
                .background(Color.Gray)
                .height(400.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = viewState.name,
            onValueChange = onNameChanged,
            modifier = widthModifier.padding(horizontal = 8.dp),
            label = {
                Text("Name")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
            ),
        )
        OutlinedTextField(
            value = viewState.description,
            onValueChange = onDescriptionChanged,
            modifier = widthModifier.padding(horizontal = 8.dp),
            label = {
                Text("Description")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
            ),
            minLines = 4,
            maxLines = 8,
        )
        Button(
            onClick = onSubmitPressed,
            modifier = widthModifier
                .padding(horizontal = 8.dp)
                .height(60.dp)
                .semantics {
                    stateDescription = if (viewState.isSaving) "Saving" else "Submit"
                },
            shape = RoundedCornerShape(8.dp),
            enabled = viewState.canSubmit
        ) {
            if (viewState.isSaving) {
                CircularProgressIndicator(color = Color.Cyan)
            } else {
                Text("Submit")
            }
        }
    }
}

@Preview
@Composable
private fun PreviewUploadForm() {
    UploadForm(
        viewState = UploadViewState(
            isSaving = true,
        ),
        onNameChanged = {},
        onDescriptionChanged = {},
        onSubmitPressed = {},
    )
}