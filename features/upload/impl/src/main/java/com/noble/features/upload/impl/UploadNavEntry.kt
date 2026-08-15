package com.noble.features.upload.impl

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import com.noble.features.upload.api.UploadScreen

private const val navDurationMillis = 200

fun EntryProviderScope<NavKey>.uploadScreenEntry() {
    entry<UploadScreen>(
        metadata = NavDisplay.transitionSpec {
            fadeIn(tween(navDurationMillis)) togetherWith fadeOut(tween(navDurationMillis))
        } + NavDisplay.popTransitionSpec {
            EnterTransition.None togetherWith fadeOut(tween(navDurationMillis))
        } + NavDisplay.predictivePopTransitionSpec {
            EnterTransition.None togetherWith fadeOut(tween(navDurationMillis))
        }
    ) {
        UploadScreen()
    }
}
