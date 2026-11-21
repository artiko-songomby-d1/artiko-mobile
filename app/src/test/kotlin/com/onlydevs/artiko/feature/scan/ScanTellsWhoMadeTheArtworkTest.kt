package com.onlydevs.artiko.feature.scan

import com.onlydevs.artiko.data.MediaFile
import com.onlydevs.artiko.support.FakeArtikoApi
import com.onlydevs.artiko.support.MainDispatcherRule
import com.onlydevs.artiko.support.MockGallery
import com.onlydevs.artiko.ui.LoadState
import java.io.IOException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test

class ScanTellsWhoMadeTheArtworkTest {
    @get:Rule
    val mainDispatcher = MainDispatcherRule()

    private val photo = MediaFile("photo.jpg", "image/jpeg", byteArrayOf(1, 2, 3))

    @Test
    fun showsTheAnswerOfTheApi() {
        val viewModel = ScanViewModel(FakeArtikoApi(scanResult = MockGallery.scanOfHumanWork))

        viewModel.scan(photo)

        assertEquals(LoadState.Loaded(MockGallery.scanOfHumanWork), viewModel.state.value)
    }

    @Test
    fun reportsAFailureAndLetsTheVisitorScanAgain() {
        val viewModel = ScanViewModel(FakeArtikoApi(failure = IOException("offline")))

        viewModel.scan(photo)
        assertEquals(LoadState.Failed("offline"), viewModel.state.value)

        viewModel.reset()
        assertNull(viewModel.state.value)
    }
}
