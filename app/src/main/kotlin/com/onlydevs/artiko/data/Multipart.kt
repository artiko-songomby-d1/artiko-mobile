package com.onlydevs.artiko.data

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

/** What an artist types before publishing a work. */
data class WorkDraft(val title: String, val story: String, val category: String, val expositionId: String?)

fun MediaFile.asPart(field: String): MultipartBody.Part =
    MultipartBody.Part.createFormData(field, name, bytes.toRequestBody(mimeType.toMediaType()))

fun workParts(draft: WorkDraft, media: MediaFile): List<MultipartBody.Part> = buildList {
    add(MultipartBody.Part.createFormData("title", draft.title))
    add(MultipartBody.Part.createFormData("category", draft.category))
    if (draft.story.isNotBlank()) add(MultipartBody.Part.createFormData("story", draft.story))
    draft.expositionId?.let { add(MultipartBody.Part.createFormData("exposition_id", it)) }
    add(media.asPart("file"))
}
