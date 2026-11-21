package com.onlydevs.artiko.feature.arttilt

import com.onlydevs.artiko.ui.nextTilt
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ArtworkTiltsWithHorizontalDragTest {
    @Test
    fun turnsTheArtworkTowardTheDragDirection() {
        assertTrue(nextTilt(current = 0f, dragPixels = 40f) > 0f)
        assertTrue(nextTilt(current = 0f, dragPixels = -40f) < 0f)
    }

    @Test
    fun neverTurnsPastTheLimit() {
        val tilt = nextTilt(current = 24f, dragPixels = 10_000f)

        assertEquals(25f, tilt, 0f)
        assertEquals(-25f, nextTilt(current = -24f, dragPixels = -10_000f), 0f)
    }
}
