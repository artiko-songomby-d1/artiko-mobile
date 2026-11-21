package com.onlydevs.artiko.feature.artview

import com.onlydevs.artiko.support.ScreenPreviewTest
import com.onlydevs.artiko.support.sampleWork
import org.junit.Test

class ArtViewScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesArtViewScreen() {
        capture("art-view") { ArtViewPage(sampleWork(), onExit = {}, onOpenArtist = {}) }
    }
}
