package com.onlydevs.artiko.feature.gallerydepth

import com.onlydevs.artiko.ui.depthPose
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ScrollDepthTiltsItemsAwayFromTheCenterTest {
    private val viewportCenter = 500f
    private val viewportHeight = 1000f

    @Test
    fun keepsTheCenteredItemFlatAndOpaque() {
        val pose = depthPose(itemCenter = 500f, viewportCenter, viewportHeight)

        assertEquals(0f, pose.rotationX, 0f)
        assertEquals(1f, pose.scale, 0f)
        assertEquals(1f, pose.alpha, 0f)
    }

    @Test
    fun leansItemsAboveTheCenterBackAndItemsBelowForward() {
        val above = depthPose(itemCenter = 100f, viewportCenter, viewportHeight)
        val below = depthPose(itemCenter = 900f, viewportCenter, viewportHeight)

        assertTrue(above.rotationX > 0f)
        assertEquals(-above.rotationX, below.rotationX, 0f)
    }

    @Test
    fun shrinksAndFadesItemsTowardTheEdges() {
        val near = depthPose(itemCenter = 600f, viewportCenter, viewportHeight)
        val far = depthPose(itemCenter = 1000f, viewportCenter, viewportHeight)

        assertTrue(far.scale < near.scale)
        assertTrue(far.alpha < near.alpha)
    }
}
