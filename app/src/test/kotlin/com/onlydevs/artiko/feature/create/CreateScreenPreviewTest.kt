package com.onlydevs.artiko.feature.create

import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Test

class CreateScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesCreateScreen() {
        capture("create") { CreateScreen(onScan = {}, onUpload = {}, onRecord = {}) }
    }
}
