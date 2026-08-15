package com.noble.features.upload.impl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
private fun UploadForm(
    viewState: UploadViewState,
    onNameChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    onSubmitPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        // TODO Replace with item image.
        Box(
            modifier = Modifier
                .background(Color.Gray)
                .heightIn(min = 400.dp, max = 400.dp)
                .widthIn(min = 500.dp, max = 500.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = viewState.name,
            onValueChange = onNameChanged,
            label = {
                Text("Name")
            }
        )
        OutlinedTextField(
            value = viewState.description,
            onValueChange = onDescriptionChanged,
            label = {
                Text("Description")
            },
            minLines = 4,
            maxLines = 8,
        )
        Button(
            onClick = onSubmitPressed,
            modifier = Modifier
                .widthIn(min = 200.dp, max = 300.dp)
                .heightIn(min = 60.dp, max = 60.dp)
                .fillMaxWidth()
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