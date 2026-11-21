package com.onlydevs.artiko.feature.settings

import com.onlydevs.artiko.data.auth.SignedInUser
import com.onlydevs.artiko.data.settings.AppSettings
import com.onlydevs.artiko.support.ScreenPreviewTest
import org.junit.Test

class SettingsScreenPreviewTest : ScreenPreviewTest() {
    @Test
    fun capturesTheList() {
        capture("settings") { SettingsScreen(onOpen = {}, onBack = {}) }
    }

    @Test
    fun capturesTheAccessibilityPage() {
        capture("settings-accessibility") { AccessibilityPage(AppSettings(), onChange = {}) }
    }

    @Test
    fun capturesTheDataPage() {
        capture("settings-data") { DataPage(AppSettings(), onChange = {}, cacheCleared = true, onClearCache = {}) }
    }

    @Test
    fun capturesTheSignInForm() {
        capture("settings-account") {
            AccountPage(null, AccountState(), onSignIn = { _, _ -> }, onSignUp = { _, _, _ -> }, onSignOut = {})
        }
    }

    @Test
    fun capturesTheSignedInAccount() {
        val user = SignedInUser("Rado", "rado@example.test")
        capture("settings-account-in") {
            AccountPage(user, AccountState(), onSignIn = { _, _ -> }, onSignUp = { _, _, _ -> }, onSignOut = {})
        }
    }
}
