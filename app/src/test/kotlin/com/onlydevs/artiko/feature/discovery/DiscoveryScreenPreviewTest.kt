package com.onlydevs.artiko.feature.discovery

import com.onlydevs.artiko.support.ScreenPreviewTest
import com.onlydevs.artiko.support.sampleExposition
import com.onlydevs.artiko.support.sampleWork
import org.junit.Test

class DiscoveryScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesDiscoveryScreen() {
        val content = DiscoveryContent(listOf(sampleExposition()), listOf(sampleWork(), sampleWork(id = "work-2")))
        capture("discovery") {
            DiscoveryPage(content, onOpenHallOfFame = {}, onOpenExposition = {}, onOpenArtView = {})
        }
    }
}
