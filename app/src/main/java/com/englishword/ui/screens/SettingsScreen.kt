package com.englishword.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.englishword.ui.theme.EnglishWordTheme

@Composable
fun SettingsScreen(
    // These would be state holders and event handlers from a ViewModel
    isDarkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit,
    ttsLocale: String,
    onTtsLocaleChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Settings", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(32.dp))

        // Dark Mode Setting
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Dark Mode", modifier = Modifier.weight(1f))
            Switch(
                checked = isDarkMode,
                onCheckedChange = onDarkModeChange
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // TTS Locale Setting (Simplified for now)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("TTS Accent (Locale)", modifier = Modifier.weight(1f))
            // A real implementation would use a DropdownMenu or a dialog
            Text(ttsLocale)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Import Words Feature Placeholder
        Button(
            onClick = { /* TODO: Implement file picker */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Import Words from File")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    EnglishWordTheme {
        SettingsScreen(
            isDarkMode = false,
            onDarkModeChange = {},
            ttsLocale = "US",
            onTtsLocaleChange = {}
        )
    }
}
