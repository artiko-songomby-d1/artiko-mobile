package com.onlydevs.artiko.feature.settings

import androidx.annotation.StringRes
import com.onlydevs.artiko.R
import kotlinx.serialization.Serializable

@Serializable
enum class SettingsSection(@StringRes val title: Int) {
    Appearance(R.string.settings_appearance),
    Accessibility(R.string.settings_accessibility),
    Data(R.string.settings_data),
    Account(R.string.settings_account),
    About(R.string.settings_about),
}
