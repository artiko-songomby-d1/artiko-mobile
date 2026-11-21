package com.onlydevs.artiko.feature.exposition

import com.onlydevs.artiko.support.MockGallery
import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Test

class ExpositionScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesExpositionScreen() {
        val exposition = MockGallery.light
        val content = ExpositionContent(exposition, MockGallery.worksIn(exposition))
        capture("exposition") { ExpositionPage(content, onOpenArtView = {}) }
    }
}
