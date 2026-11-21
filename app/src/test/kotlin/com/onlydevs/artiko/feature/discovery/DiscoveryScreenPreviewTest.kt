package com.onlydevs.artiko.feature.discovery

import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Test

class DiscoveryScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesDiscoveryScreen() {
        capture("discovery") { DiscoveryScreen({}, {}, {}) }
    }
}
