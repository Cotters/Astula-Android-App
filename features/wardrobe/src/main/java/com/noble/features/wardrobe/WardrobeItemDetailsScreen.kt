package com.noble.features.wardrobe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WardrobeItemDetailsScreen(
    itemId: Int,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {
            Text("Item Detail view coming soon...", style = MaterialTheme.typography.titleLarge)
            Text("Tapped on item with ID: $itemId.")
        }
    }

}