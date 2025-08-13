package com.englishword

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.englishword.ui.theme.EnglishWordTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnglishWordApp()
        }
    }
}

import androidx.compose.ui.platform.LocalContext
import com.englishword.ui.AppNavigation

@Composable
fun EnglishWordApp() {
    val application = LocalContext.current.applicationContext as EnglishWordApplication
    EnglishWordTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AppNavigation(application = application)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EnglishWordAppPreview() {
    EnglishWordTheme {
        // This preview won't show much as navigation is complex to preview,
        // but it ensures the theme is applied correctly.
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Text("App Preview")
        }
    }
}
