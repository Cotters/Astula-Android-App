package com.noble.features.wardrobe.impl

import androidx.compose.foundation.clickable
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
    onItemTapped: (itemId: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
    ) { paddingValues ->
        WardrobeView(
            viewState = viewState,
            onItemTapped = onItemTapped,
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 8.dp, vertical = 12.dp)
        )
    }
}

@Composable
private fun WardrobeView(
    viewState: WardrobeState,
    onItemTapped: (itemId: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(text = "Your Wardrobe", style = MaterialTheme.typography.titleLarge)
        when (viewState) {
            WardrobeState.Loading -> AstulaLoadingView()
            is WardrobeState.Loaded -> ClothingItemListView(viewState.items, onItemTapped)
            is WardrobeState.Error -> Text(viewState.message)
        }
    }
}

@Composable
private fun ClothingItemListView(
    clothingItems: List<ClothingItem>,
    onItemTapped: (itemId: Int) -> Unit,
) {
    LazyColumn {
        items(clothingItems, key = { it.id }) { item ->
            Column(
                modifier = Modifier.clickable {
                    onItemTapped(item.id)
                },
            ) {
                Text(item.name)
                Text(item.description)
                HorizontalDivider()
            }
        }
    }
}