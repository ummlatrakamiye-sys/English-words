package com.englishword.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.englishword.ui.theme.EnglishWordTheme

@Composable
fun DashboardScreen(
    onNavigateToReview: () -> Unit,
    onNavigateToGames: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Dashboard", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(32.dp))

        // Placeholder for stats
        Text("Mastered Words: 50%", style = MaterialTheme.typography.bodyLarge)
        Text("Words to Review: 10", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onNavigateToReview) {
            Text("Start Review")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigateToGames) {
            Text("Games")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigateToSettings) {
            Text("Settings")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    EnglishWordTheme {
        DashboardScreen({}, {}, {})
    }
}
