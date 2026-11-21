package com.onlydevs.artiko.feature.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onlydevs.artiko.R
import com.onlydevs.artiko.ui.ArtikoButton
import com.onlydevs.artiko.ui.theme.ArtikoText
import com.onlydevs.artiko.ui.theme.ArtikoTheme

@Composable
fun AboutPage(versionName: String, serverUrl: String, onOpenWeb: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Artiko", style = MaterialTheme.typography.headlineLarge)
        Text(stringResource(R.string.about_tagline), style = ArtikoText.meta)
        Text(stringResource(R.string.about_version, versionName), style = MaterialTheme.typography.bodyLarge)
        Text(stringResource(R.string.about_server, serverUrl), style = MaterialTheme.typography.bodyMedium)
        ArtikoButton(text = stringResource(R.string.about_open_web), onClick = onOpenWeb)
    }
}

@Preview(showBackground = true)
@Composable
private fun AboutPagePreview() {
    ArtikoTheme {
        AboutPage(versionName = "1.0.280", serverUrl = "https://artiko-api.onrender.com/", onOpenWeb = {})
    }
}
