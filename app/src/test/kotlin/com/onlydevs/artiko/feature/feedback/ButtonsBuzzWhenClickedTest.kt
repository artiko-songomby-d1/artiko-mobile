package com.onlydevs.artiko.feature.feedback

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.onlydevs.artiko.support.ScreenPreviewTest
import com.onlydevs.artiko.ui.ArtikoButton
import com.onlydevs.artiko.ui.ExpositionChip
import com.onlydevs.artiko.ui.theme.ArtikoTheme
import org.junit.Assert.assertEquals
import org.junit.Test

class ButtonsBuzzWhenClickedTest : ScreenPreviewTest() {
    private class CountingHaptics : HapticFeedback {
        var buzzes = 0

        override fun performHapticFeedback(hapticFeedbackType: HapticFeedbackType) {
            buzzes++
        }
    }

    @Test
    fun everyTapRunsTheActionAndBuzzesOnce() {
        val haptics = CountingHaptics()
        var taps = 0
        composeRule.setContent {
            CompositionLocalProvider(LocalHapticFeedback provides haptics) {
                ArtikoTheme {
                    Column {
                        ArtikoButton(text = "Go", onClick = { taps++ })
                        ExpositionChip(title = "Tag", onClick = { taps++ })
                    }
                }
            }
        }

        composeRule.onNodeWithText("Go").performClick()
        composeRule.onNodeWithText("Tag").performClick()

        assertEquals(2, taps)
        assertEquals(2, haptics.buzzes)
    }
}
