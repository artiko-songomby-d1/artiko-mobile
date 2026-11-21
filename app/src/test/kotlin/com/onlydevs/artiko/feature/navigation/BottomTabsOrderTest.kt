package com.onlydevs.artiko.feature.navigation

import org.junit.Assert.assertEquals
import org.junit.Test

class BottomTabsOrderTest {
    @Test
    fun showsDiscoveryThenCreateThenLearnThenProfile() {
        val labels = TopLevelTab.entries.map { it.label }

        assertEquals(listOf("Discovery", "Create", "Learn", "Profile"), labels)
    }
}
