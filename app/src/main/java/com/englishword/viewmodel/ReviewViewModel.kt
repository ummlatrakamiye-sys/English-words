package com.englishword.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.englishword.data.Word
import com.englishword.repository.WordRepository
import com.englishword.srs.SrsCalculator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ReviewViewModel(private val repository: WordRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<ReviewUiState>(ReviewUiState.Loading)
    val uiState: StateFlow<ReviewUiState> = _uiState.asStateFlow()

    private var dueWords: List<Word> = emptyList()
    private var currentWordIndex = 0

    init {
        loadDueWords()
    }

    private fun loadDueWords() {
        viewModelScope.launch {
            _uiState.value = ReviewUiState.Loading
            // Get the first emission of the due words list
            dueWords = repository.getDueWords(System.currentTimeMillis()).first()
            if (dueWords.isNotEmpty()) {
                _uiState.value = ReviewUiState.Success(dueWords[currentWordIndex])
            } else {
                _uiState.value = ReviewUiState.Empty
            }
        }
    }

    fun onAnswer(quality: SrsCalculator.AnswerQuality) {
        val currentWord = (uiState.value as? ReviewUiState.Success)?.word ?: return

        viewModelScope.launch {
            // Calculate next review and update the word in the database
            val updatedWord = SrsCalculator.calculateNextReview(currentWord, quality)
            repository.update(updatedWord)

            // Move to the next word
            currentWordIndex++
            if (currentWordIndex < dueWords.size) {
                _uiState.value = ReviewUiState.Success(dueWords[currentWordIndex])
            } else {
                _uiState.value = ReviewUiState.Completed
            }
        }
    }
}

// Sealed interface to represent the different states of the review screen
sealed interface ReviewUiState {
    object Loading : ReviewUiState
    object Empty : ReviewUiState // No words to review
    object Completed : ReviewUiState // Finished all due words for this session
    data class Success(val word: Word) : ReviewUiState
}
