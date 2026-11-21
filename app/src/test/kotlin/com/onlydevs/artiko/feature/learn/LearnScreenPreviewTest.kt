package com.onlydevs.artiko.feature.learn

import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Test

class LearnScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesLearnScreen() {
        capture("learn") { LearnScreen() }
    }
}
