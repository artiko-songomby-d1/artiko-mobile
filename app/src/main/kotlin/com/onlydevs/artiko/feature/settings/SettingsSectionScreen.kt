package com.onlydevs.artiko.feature.settings

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.SingletonImageLoader
import com.onlydevs.artiko.BuildConfig
import com.onlydevs.artiko.ui.FEED_PADDING
import com.onlydevs.artiko.ui.FEED_SPACING
import com.onlydevs.artiko.ui.ScreenHeader
import com.onlydevs.artiko.ui.appViewModel

/** One page of the settings, chosen by [section], with its own working controls. */
@Composable
fun SettingsSectionScreen(section: SettingsSection, onBack: () -> Unit) {
    val viewModel = appViewModel { app -> SettingsViewModel(app.settingsRepository, app.authRepository) }
    val settings by viewModel.settings.collectAsStateWithLifecycle()
    val user by viewModel.user.collectAsStateWithLifecycle()
    val account by viewModel.account.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var cacheCleared by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(FEED_PADDING),
        verticalArrangement = Arrangement.spacedBy(FEED_SPACING),
    ) {
        ScreenHeader(title = stringResource(section.title), onBack = onBack)
        when (section) {
            SettingsSection.Appearance -> AppearancePage(settings, viewModel::change)

            SettingsSection.Accessibility -> AccessibilityPage(settings, viewModel::change)

            SettingsSection.Data -> DataPage(
                settings = settings,
                onChange = viewModel::change,
                cacheCleared = cacheCleared,
                onClearCache = {
                    val loader = SingletonImageLoader.get(context)
                    loader.memoryCache?.clear()
                    loader.diskCache?.clear()
                    cacheCleared = true
                },
            )

            SettingsSection.Account -> AccountPage(
                user = user,
                state = account,
                onSignIn = viewModel::signIn,
                onSignUp = viewModel::signUp,
                onSignOut = viewModel::signOut,
            )

            SettingsSection.About -> AboutPage(
                versionName = BuildConfig.VERSION_NAME,
                serverUrl = BuildConfig.API_BASE_URL,
                onOpenWeb = {
                    val page = Intent(Intent.ACTION_VIEW, BuildConfig.AUTH_BASE_URL.toUri())
                    context.startActivity(page.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                },
            )
        }
    }
}
