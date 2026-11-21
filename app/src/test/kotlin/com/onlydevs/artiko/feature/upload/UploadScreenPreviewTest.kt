package com.onlydevs.artiko.feature.upload

import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Test

class UploadScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesUploadScreen() {
        capture("upload") { UploadScreen() }
    }
}
