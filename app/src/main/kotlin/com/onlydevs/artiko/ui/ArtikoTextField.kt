package com.onlydevs.artiko.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.ui.theme.ArtikoTheme

private const val FIELD_ALPHA = 0.5f

/** A text input in the style of the artiko-web forms: a thin outline that darkens when focused. */
@Composable
fun ArtikoTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(label) },
        singleLine = singleLine,
        shape = MaterialTheme.shapes.small,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
            focusedBorderColor = MaterialTheme.colorScheme.outline,
            unfocusedContainerColor = Color.White.copy(alpha = FIELD_ALPHA),
            focusedContainerColor = Color.White.copy(alpha = FIELD_ALPHA),
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun ArtikoTextFieldPreview() {
    ArtikoTheme {
        ArtikoTextField(value = "Terraces at Noon", onValueChange = {}, label = "Title")
    }
}
