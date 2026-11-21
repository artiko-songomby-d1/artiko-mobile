package com.onlydevs.artiko.feature.artview

import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Test

class ArtViewScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesArtViewScreen() {
        capture("artview") { ArtViewScreen({}) }
    }
}
