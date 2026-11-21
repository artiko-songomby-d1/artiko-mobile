package com.onlydevs.artiko.feature.artist

import com.onlydevs.artiko.support.ScreenPreviewTest
import com.onlydevs.artiko.support.sampleArtist
import com.onlydevs.artiko.support.sampleExposition
import com.onlydevs.artiko.support.sampleWork
import org.junit.Test

class ArtistScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesArtistScreen() {
        val content = ArtistContent(sampleArtist(), listOf(sampleExposition()), listOf(sampleWork()))
        capture("artist") { ArtistPage(content, onOpenExposition = {}, onOpenArtView = {}) }
    }
}
