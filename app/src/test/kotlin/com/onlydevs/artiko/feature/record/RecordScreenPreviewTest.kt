package com.onlydevs.artiko.feature.record

import com.onlydevs.artiko.support.ScreenPreviewTest
import com.onlydevs.artiko.ui.GeneratedCover
import org.junit.Test

class RecordScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesARecordingInProgress() {
        capture("record") {
            RecordPage(true, 7, false, { GeneratedCover(seed = "recording") }, onToggle = {}, onUpload = {})
        }
    }

    @Test
    fun capturesAFinishedTake() {
        capture("record-done") {
            RecordPage(false, 0, true, { GeneratedCover(seed = "recording") }, onToggle = {}, onUpload = {})
        }
    }
}
