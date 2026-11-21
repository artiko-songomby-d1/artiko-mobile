package com.onlydevs.artiko.feature.scan

import com.onlydevs.artiko.support.MockGallery
import com.onlydevs.artiko.support.ScreenPreviewTest
import com.onlydevs.artiko.ui.GeneratedCover
import com.onlydevs.artiko.ui.LoadState
import org.junit.Test

class ScanScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesTheViewfinder() {
        capture("scan") {
            ScanPage(null, { GeneratedCover(seed = "viewfinder") }, onScan = {}, onPick = {}, onReset = {})
        }
    }

    @Test
    fun capturesTheAnswerForAKnownWork() {
        val state = LoadState.Loaded(MockGallery.scanOfKnownWork)
        capture("scan-result") {
            ScanPage(state, { GeneratedCover(seed = "viewfinder") }, onScan = {}, onPick = {}, onReset = {})
        }
    }
}
