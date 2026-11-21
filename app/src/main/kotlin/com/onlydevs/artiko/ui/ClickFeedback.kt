package com.onlydevs.artiko.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback

private const val PRESSED_SCALE = 0.95f

/** Shrinks the element while a finger is on it, so every tap visibly pushes it. */
@Composable
fun Modifier.pressScale(interactionSource: InteractionSource): Modifier {
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(targetValue = if (pressed) PRESSED_SCALE else 1f, label = "pressScale")
    return graphicsLayer {
        scaleX = scale
        scaleY = scale
    }
}

/** Wraps a click handler so the phone also gives a short buzz when it runs. */
@Composable
fun rememberHapticClick(onClick: () -> Unit): () -> Unit {
    val haptics = LocalHapticFeedback.current
    val latest by rememberUpdatedState(onClick)
    return remember(haptics) {
        {
            haptics.performHapticFeedback(HapticFeedbackType.ContextClick)
            latest()
        }
    }
}
