package com.onlydevs.artiko.feature.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.ui.rememberHapticClick
import com.onlydevs.artiko.ui.theme.ArtikoTheme

/** A labelled switch; the whole row can be tapped. */
@Composable
fun SettingsToggle(label: String, checked: Boolean, onChange: (Boolean) -> Unit, hint: String? = null) {
    val toggle = rememberHapticClick { onChange(!checked) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(label, style = MaterialTheme.typography.bodyLarge)
            if (hint != null) Text(hint, style = MaterialTheme.typography.bodySmall)
        }
        Switch(checked = checked, onCheckedChange = { toggle() })
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsTogglePreview() {
    ArtikoTheme {
        SettingsToggle(label = "Data saver", checked = true, onChange = {}, hint = "Loads smaller pictures.")
    }
}
