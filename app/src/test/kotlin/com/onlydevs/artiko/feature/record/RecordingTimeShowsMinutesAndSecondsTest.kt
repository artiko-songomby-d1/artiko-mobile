package com.onlydevs.artiko.feature.record

import org.junit.Assert.assertEquals
import org.junit.Test

class RecordingTimeShowsMinutesAndSecondsTest {
    @Test
    fun padsSecondsAndRollsOverToMinutes() {
        assertEquals("00:07", formatElapsed(7))
        assertEquals("01:05", formatElapsed(65))
        assertEquals("10:00", formatElapsed(600))
    }
}
