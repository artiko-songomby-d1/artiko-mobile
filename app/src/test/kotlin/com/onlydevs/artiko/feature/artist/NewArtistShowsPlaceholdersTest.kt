package com.onlydevs.artiko.feature.artist

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import com.onlydevs.artiko.support.ScreenPreviewTest
import com.onlydevs.artiko.support.sampleArtist
import org.junit.Test

class NewArtistShowsPlaceholdersTest : ScreenPreviewTest() {
    @Test
    fun showsAPlaceholderForExpositionsAndForArt() {
        val content = ArtistContent(sampleArtist(), expositions = emptyList(), works = emptyList())
        capture("artist-empty") { ArtistPage(content, onOpenExposition = {}, onOpenArtView = {}) }

        composeRule.onNodeWithText("No expositions yet").assertIsDisplayed()
        composeRule.onNodeWithText("No art from this artist yet").assertIsDisplayed()
    }
}
