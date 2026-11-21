package com.onlydevs.artiko.feature.create

import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Assert.assertEquals
import org.junit.Test

class CreateButtonsOpenTheirPagesTest : ScreenPreviewTest() {
    @Test
    fun eachButtonAsksForItsOwnPage() {
        val opened = mutableListOf<String>()
        capture("create-buttons") {
            CreateScreen(
                onScan = { opened += "scan" },
                onUpload = { opened += "upload" },
                onRecord = { opened += "record" },
            )
        }

        listOf("Scan", "Upload", "Record").forEach { composeRule.onNodeWithText(it).performClick() }

        assertEquals(listOf("scan", "upload", "record"), opened)
    }
}
