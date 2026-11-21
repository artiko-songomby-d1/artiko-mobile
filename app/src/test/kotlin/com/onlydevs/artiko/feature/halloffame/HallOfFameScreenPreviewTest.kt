package com.onlydevs.artiko.feature.halloffame

import com.onlydevs.artiko.support.MockGallery
import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Test

class HallOfFameScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesHallOfFameScreen() {
        capture("hall-of-fame") { HallOfFamePage(MockGallery.hallOfFame, onOpenArtView = {}) }
    }
}
