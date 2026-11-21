package com.onlydevs.artiko.feature.artist

import com.onlydevs.artiko.support.MockGallery
import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Test

class ArtistScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesArtistScreen() {
        val artist = MockGallery.rado
        val content = ArtistContent(artist, MockGallery.expositionsOf(artist), MockGallery.worksOf(artist))
        capture("artist") { ArtistPage(content, onOpenExposition = {}, onOpenArtView = {}) }
    }
}
