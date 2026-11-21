package com.onlydevs.artiko.feature.exposition

import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Test

class ExpositionScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesExpositionScreen() {
        capture("exposition") { ExpositionScreen({}) }
    }
}
