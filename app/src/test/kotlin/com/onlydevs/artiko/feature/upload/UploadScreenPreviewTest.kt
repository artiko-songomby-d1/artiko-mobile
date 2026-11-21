package com.onlydevs.artiko.feature.upload

import com.onlydevs.artiko.data.WorkDraft
import com.onlydevs.artiko.support.MockGallery
import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Test

class UploadScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesAFilledInForm() {
        val options = UploadOptions(MockGallery.categories, MockGallery.expositions)
        val draft = WorkDraft("Terraces at Noon", "Oil on canvas", "painting", MockGallery.light.id)
        capture("upload") {
            UploadPage(options, draft, "terraces.jpg", null, onDraftChange = {}, onPickMedia = {}, onPublish = {})
        }
    }
}
