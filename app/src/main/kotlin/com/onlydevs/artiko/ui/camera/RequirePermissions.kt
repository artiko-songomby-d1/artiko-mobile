package com.onlydevs.artiko.ui.camera

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.onlydevs.artiko.R
import com.onlydevs.artiko.ui.ArtikoButton
import com.onlydevs.artiko.ui.theme.ArtikoText
import com.onlydevs.artiko.ui.theme.ArtikoTheme

private fun Context.openAppSettings() {
    val page = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, "package:$packageName".toUri())
    startActivity(page.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
}

private val PROMPT_PADDING = 24.dp
private val PROMPT_SPACING = 16.dp

/** Shows [content] once every permission is granted, and asks for them with a friendly prompt until then. */
@Composable
fun RequirePermissions(permissions: List<String>, rationale: String, content: @Composable () -> Unit) {
    val context = LocalContext.current
    fun allGranted() = permissions.all {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }
    var granted by remember { mutableStateOf(allGranted()) }
    var refused by remember { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        granted = allGranted()
        refused = !granted
    }
    // The visitor may have allowed the permission in Android's settings and come back: check again.
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) { granted = allGranted() }
    if (granted) {
        content()
    } else {
        PermissionPrompt(
            rationale = rationale,
            // After a refusal Android may not ask again, so the only way left is the app's settings page.
            onAllow = {
                if (refused) context.openAppSettings() else launcher.launch(permissions.toTypedArray())
            },
            showsSettings = refused,
        )
    }
}

@Composable
private fun PermissionPrompt(rationale: String, onAllow: () -> Unit, showsSettings: Boolean) {
    Column(
        modifier = Modifier.fillMaxSize().padding(PROMPT_PADDING),
        verticalArrangement = Arrangement.spacedBy(PROMPT_SPACING, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = rationale, style = ArtikoText.meta, textAlign = TextAlign.Center)
        ArtikoButton(text = if (showsSettings) stringResource(R.string.open_settings) else "Allow", onClick = onAllow)
    }
}

@Preview(showBackground = true)
@Composable
private fun PermissionPromptPreview() {
    ArtikoTheme {
        PermissionPrompt(rationale = "Artiko needs the camera to scan an artwork", onAllow = {}, showsSettings = false)
    }
}
