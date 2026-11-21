package com.onlydevs.artiko.feature.discovery

import com.onlydevs.artiko.support.MockGallery
import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Test

class DiscoveryScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesDiscoveryScreen() {
        val content = DiscoveryContent(MockGallery.expositions, MockGallery.works)
        capture("discovery") {
            DiscoveryPage(content, onOpenHallOfFame = {}, onOpenExposition = {}, onOpenArtView = {})
        }
    }
}
