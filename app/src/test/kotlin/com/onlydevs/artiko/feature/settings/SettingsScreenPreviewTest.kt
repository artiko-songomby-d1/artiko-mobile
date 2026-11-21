package com.onlydevs.artiko.feature.settings

import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Test

class SettingsScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesSettingsScreen() {
        capture("settings") { SettingsScreen() }
    }
}
