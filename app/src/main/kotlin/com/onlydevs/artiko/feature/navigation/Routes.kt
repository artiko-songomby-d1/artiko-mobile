package com.onlydevs.artiko.feature.navigation

import com.onlydevs.artiko.feature.settings.SettingsSection
import kotlinx.serialization.Serializable

@Serializable
data object DiscoveryRoute

@Serializable
data object CreateRoute

@Serializable
data object LearnRoute

@Serializable
data object ProfileRoute

@Serializable
data object ScanRoute

@Serializable
data class UploadRoute(val attachmentUri: String? = null)

@Serializable
data object RecordRoute

@Serializable
data object HallOfFameRoute

@Serializable
data object SettingsRoute

@Serializable
data class SettingsSectionRoute(val section: SettingsSection)

@Serializable
data class ArtistRoute(val artistId: String)

@Serializable
data class ExpositionRoute(val expositionId: String)

@Serializable
data class ArtViewRoute(val workId: String)
