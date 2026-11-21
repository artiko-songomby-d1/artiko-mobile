package com.onlydevs.artiko.feature.scan

import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Test

class ScanScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesScanScreen() {
        capture("scan") { ScanScreen() }
    }
}
