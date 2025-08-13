package com.englishword.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.englishword.EnglishWordApplication
import com.englishword.ui.screens.DashboardScreen
import com.englishword.ui.screens.ReviewScreen
import com.englishword.ui.screens.SettingsScreen
import com.englishword.viewmodel.ReviewUiState
import com.englishword.viewmodel.ReviewViewModel
import com.englishword.viewmodel.ViewModelFactory

// Define navigation routes
object AppDestinations {
    const val DASHBOARD_ROUTE = "dashboard"
    const val REVIEW_ROUTE = "review"
    const val SETTINGS_ROUTE = "settings"
}

@Composable
fun AppNavigation(application: EnglishWordApplication) {
    val navController = rememberNavController()
    val factory = ViewModelFactory(application.repository)

    NavHost(navController = navController, startDestination = AppDestinations.DASHBOARD_ROUTE) {
        composable(AppDestinations.DASHBOARD_ROUTE) {
            DashboardScreen(
                onNavigateToReview = { navController.navigate(AppDestinations.REVIEW_ROUTE) },
                onNavigateToGames = { /* TODO: Implement Games screen */ },
                onNavigateToSettings = { navController.navigate(AppDestinations.SETTINGS_ROUTE) }
            )
        }
        composable(AppDestinations.REVIEW_ROUTE) {
            val reviewViewModel: ReviewViewModel = viewModel(factory = factory)
            val uiState by reviewViewModel.uiState.collectAsState()

            val word = when (val state = uiState) {
                is ReviewUiState.Success -> state.word
                else -> null // Loading, Empty, or Completed state
            }

            ReviewScreen(
                word = word,
                onAnswer = { quality -> reviewViewModel.onAnswer(quality) }
            )
        }
        composable(AppDestinations.SETTINGS_ROUTE) {
            // TODO: Create a SettingsViewModel to handle state
            SettingsScreen(
                isDarkMode = false,
                onDarkModeChange = {},
                ttsLocale = "US",
                onTtsLocaleChange = {}
            )
        }
    }
}
