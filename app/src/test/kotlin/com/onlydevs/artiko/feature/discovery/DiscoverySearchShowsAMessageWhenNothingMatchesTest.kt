package com.onlydevs.artiko.feature.discovery

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import com.onlydevs.artiko.support.MockGallery
import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Test

class DiscoverySearchShowsAMessageWhenNothingMatchesTest : ScreenPreviewTest() {
    @Test
    fun explainsThatNoArtMatchesTheSearch() {
        val content = DiscoveryContent(MockGallery.expositions, works = emptyList())
        capture("discovery-search-empty") {
            DiscoveryPage(content, "zzz", {}, onOpenHallOfFame = {}, onOpenExposition = {}, onOpenArtView = {})
        }

        composeRule.onNodeWithText("No art matches your search").assertIsDisplayed()
    }
}
