package com.onlydevs.artiko.feature.discovery

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Test

class DiscoveryWithoutWorksShowsPlaceholderTest : ScreenPreviewTest() {
    @Test
    fun showsAPlaceholderInsteadOfAnEmptyFeed() {
        val content = DiscoveryContent(expositions = emptyList(), works = emptyList())
        capture("discovery-empty") {
            DiscoveryPage(content, onOpenHallOfFame = {}, onOpenExposition = {}, onOpenArtView = {})
        }

        composeRule.onNodeWithText("No art yet").assertIsDisplayed()
    }
}
