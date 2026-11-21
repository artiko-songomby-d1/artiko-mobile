package com.onlydevs.artiko.feature.profile

import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Test

class ProfileScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesProfileScreen() {
        capture("profile") { ProfileScreen({}) }
    }
}
