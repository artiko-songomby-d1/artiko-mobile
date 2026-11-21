package com.onlydevs.artiko.feature.exposition

import com.onlydevs.artiko.support.ScreenPreviewTest
import com.onlydevs.artiko.support.sampleExposition
import com.onlydevs.artiko.support.sampleWork
import org.junit.Test

class ExpositionScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesExpositionScreen() {
        val content = ExpositionContent(sampleExposition(), listOf(sampleWork(), sampleWork(id = "work-2")))
        capture("exposition") { ExpositionPage(content, onOpenArtView = {}) }
    }
}
