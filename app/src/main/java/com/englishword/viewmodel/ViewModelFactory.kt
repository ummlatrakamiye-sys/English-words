package com.englishword.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.englishword.repository.WordRepository

class ViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReviewViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReviewViewModel(repository) as T
        }
        // TODO: Add other ViewModels here as the app grows
        // if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) { ... }
        throw IllegalArgumentException("Unknown ViewModel class: \${modelClass.name}")
    }
}
