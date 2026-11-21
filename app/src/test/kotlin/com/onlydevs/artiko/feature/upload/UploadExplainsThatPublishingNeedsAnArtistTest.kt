package com.onlydevs.artiko.feature.upload

import com.onlydevs.artiko.data.MediaFile
import com.onlydevs.artiko.data.WorkDraft
import com.onlydevs.artiko.support.FakeArtikoApi
import com.onlydevs.artiko.support.MainDispatcherRule
import com.onlydevs.artiko.support.MockGallery
import com.onlydevs.artiko.ui.LoadState
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class UploadExplainsThatPublishingNeedsAnArtistTest {
    @get:Rule
    val mainDispatcher = MainDispatcherRule()

    private val draft = WorkDraft("Terraces at Noon", "", "painting", null)
    private val media = MediaFile("terraces.jpg", "image/jpeg", byteArrayOf(1, 2, 3))

    @Test
    fun offersTheCategoriesAndTheExpositions() {
        val api = FakeArtikoApi(categories = MockGallery.categories, expositions = MockGallery.expositions)

        val viewModel = UploadViewModel(api)

        val expected = UploadOptions(MockGallery.categories, MockGallery.expositions)
        assertEquals(LoadState.Loaded(expected), viewModel.state.value)
    }

    @Test
    fun publishesTheWork() {
        val viewModel = UploadViewModel(FakeArtikoApi(published = MockGallery.terraces))

        viewModel.publish(draft, media)

        assertEquals(LoadState.Loaded(MockGallery.terraces), viewModel.publication.value)
    }

    @Test
    fun asksForASignInWhenTheApiRefusesTheVisitor() {
        val refusal = HttpException(Response.error<Any>(401, "".toResponseBody()))
        val viewModel = UploadViewModel(FakeArtikoApi(failure = refusal))

        viewModel.publish(draft, media)

        assertEquals(LoadState.Failed(SIGN_IN_MESSAGE), viewModel.publication.value)
    }
}
