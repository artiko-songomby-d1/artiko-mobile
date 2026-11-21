package com.onlydevs.artiko.feature.artview

import com.onlydevs.artiko.support.MockGallery
import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Test

class ArtViewScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesAnImageWork() {
        capture("art-view-image") { ArtViewPage(MockGallery.terraces, onExit = {}, onOpenArtist = {}) }
    }

    @Test
    fun capturesAVideoWork() {
        capture("art-view-video") { ArtViewPage(MockGallery.tuning, onExit = {}, onOpenArtist = {}) }
    }
}
