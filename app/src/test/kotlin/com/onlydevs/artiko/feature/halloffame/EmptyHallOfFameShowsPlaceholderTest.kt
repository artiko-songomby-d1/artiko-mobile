package com.onlydevs.artiko.feature.halloffame

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Test

class EmptyHallOfFameShowsPlaceholderTest : ScreenPreviewTest() {
    @Test
    fun showsAPlaceholderWhenNobodyVotedYet() {
        capture("hall-of-fame-empty") { HallOfFamePage(halls = emptyList(), onOpenArtView = {}) }

        composeRule.onNodeWithText("No votes yet this month").assertIsDisplayed()
    }
}
