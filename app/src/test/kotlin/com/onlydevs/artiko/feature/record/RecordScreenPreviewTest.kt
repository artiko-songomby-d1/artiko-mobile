package com.onlydevs.artiko.feature.record

import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Test

class RecordScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesRecordScreen() {
        capture("record") { RecordScreen() }
    }
}
