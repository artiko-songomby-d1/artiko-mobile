package com.onlydevs.artiko.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MediaKind {
    @SerialName("image")
    Image,

    @SerialName("audio")
    Audio,

    @SerialName("video")
    Video,

    @SerialName("model")
    Model,
}

@Serializable
data class Work(
    val id: String,
    @SerialName("artist_id") val artistId: String,
    @SerialName("artist_name") val artistName: String,
    val title: String,
    val story: String,
    val category: String,
    @SerialName("media_url") val mediaUrl: String,
    @SerialName("media_kind") val mediaKind: MediaKind,
    @SerialName("human_proven") val humanProven: Boolean,
    val votes: Int,
    @SerialName("exposition_id") val expositionId: String? = null,
)

@Serializable
data class Artist(
    val id: String,
    @SerialName("display_name") val displayName: String,
    val bio: String,
    val region: String,
)

@Serializable
data class Exposition(
    val id: String,
    @SerialName("artist_id") val artistId: String,
    @SerialName("artist_name") val artistName: String,
    val title: String,
    val description: String,
    @SerialName("cover_url") val coverUrl: String? = null,
    @SerialName("works_count") val worksCount: Int,
)

@Serializable
data class Category(val id: String, val label: String)

@Serializable
data class HallOfFameEntry(val rank: Int, val work: Work, val votes: Int)
