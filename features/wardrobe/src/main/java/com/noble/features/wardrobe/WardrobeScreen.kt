package com.noble.features.wardrobe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.noble.presentation.AstulaLoadingView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WardrobeScreen(
    viewState: WardrobeState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 8.dp, vertical = 12.dp),
        ) {
            Text(text = "Your Wardrobe", style = MaterialTheme.typography.titleLarge)
            when (viewState) {
                WardrobeState.Loading -> AstulaLoadingView()
                is WardrobeState.Loaded -> ClothingItemListView(viewState.items)
                is WardrobeState.Error -> Text(viewState.message)
            }
        }
    }
}

@Composable
private fun ClothingItemListView(
    clothingItems: List<ClothingItem>,
) {
    LazyColumn {
        items(clothingItems, key = { it.id }) { item ->
            Column {
                Text(item.name)
                Text(item.description)
                HorizontalDivider()
            }
        }
    }
}