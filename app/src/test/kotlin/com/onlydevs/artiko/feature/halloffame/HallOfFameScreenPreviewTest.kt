package com.onlydevs.artiko.feature.halloffame

import com.onlydevs.artiko.data.HallOfFameEntry
import com.onlydevs.artiko.support.ScreenPreviewTest
import com.onlydevs.artiko.support.sampleCategory
import com.onlydevs.artiko.support.sampleWork
import org.junit.Test

class HallOfFameScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesHallOfFameScreen() {
        val halls = listOf(CategoryHall(sampleCategory(), listOf(HallOfFameEntry(1, sampleWork(), 3))))
        capture("hall-of-fame") { HallOfFamePage(halls, onOpenArtView = {}) }
    }
}
