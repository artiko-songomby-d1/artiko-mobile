package com.onlydevs.artiko.feature.artist

import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Test

class ArtistScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesArtistScreen() {
        capture("artist") { ArtistScreen({}, {}) }
    }
}
