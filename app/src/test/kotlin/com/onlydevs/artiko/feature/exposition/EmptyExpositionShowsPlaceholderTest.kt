package com.onlydevs.artiko.feature.exposition

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import com.onlydevs.artiko.support.ScreenPreviewTest
import com.onlydevs.artiko.support.sampleExposition
import org.junit.Test

class EmptyExpositionShowsPlaceholderTest : ScreenPreviewTest() {
    @Test
    fun showsAPlaceholderBelowTheExpositionTitle() {
        val content = ExpositionContent(sampleExposition(), works = emptyList())
        capture("exposition-empty") { ExpositionPage(content, onOpenArtView = {}) }

        composeRule.onNodeWithText("No art in this exposition yet").assertIsDisplayed()
    }
}
