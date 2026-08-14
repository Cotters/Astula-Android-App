package com.noble.presentation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AstulaLoadingView(modifier: Modifier = Modifier) {
    val scaleTransition by rememberInfiniteTransition().animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(600, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        )
    )
    Surface(
        modifier = modifier.fillMaxWidth(),
    ) {
        Image(
            painterResource(id = R.drawable.app_icon),
            contentDescription = "Astula Icon",
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scaleTransition
                    scaleY = scaleTransition
                }
                .size(150.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewLoading() {
    AstulaLoadingView()
}