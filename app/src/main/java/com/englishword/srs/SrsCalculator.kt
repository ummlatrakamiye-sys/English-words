package com.englishword.srs

import com.englishword.data.Word
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt

object SrsCalculator {

    // Represents the user's self-assessed answer quality
    enum class AnswerQuality {
        AGAIN, // Incorrect
        HARD,
        GOOD,
        EASY
    }

    fun calculateNextReview(word: Word, quality: AnswerQuality): Word {
        val now = System.currentTimeMillis()

        if (quality == AnswerQuality.AGAIN) {
            // If incorrect, reset progress and review again soon (e.g., in 10 minutes)
            return word.copy(
                repetitionCount = 0,
                repetitionInterval = 0,
                lastReviewDate = now + TimeUnit.MINUTES.toMillis(10),
                isMastered = false
            )
        }

        // If the answer is correct (Hard, Good, or Easy)
        val newRepetitionCount = word.repetitionCount + 1

        val newIntervalDays = when (newRepetitionCount) {
            1 -> 1
            2 -> 3
            // After the first two correct answers, use a growing factor
            else -> (word.repetitionInterval * 2.1).roundToInt()
        }.coerceAtLeast(1) // Ensure interval is at least 1 day

        // Adjust interval based on how "easy" it was
        val intervalMultiplier = when (quality) {
            AnswerQuality.HARD -> 0.8
            AnswerQuality.GOOD -> 1.0
            AnswerQuality.EASY -> 1.3
            else -> 1.0 // Should not happen here
        }

        val finalIntervalDays = (newIntervalDays * intervalMultiplier).roundToInt().coerceAtLeast(1)
        val newNextReviewDate = now + TimeUnit.DAYS.toMillis(finalIntervalDays.toLong())

        // Consider a word mastered if its interval grows beyond a certain point (e.g., 90 days)
        val isMastered = finalIntervalDays > 90

        return word.copy(
            repetitionCount = newRepetitionCount,
            repetitionInterval = finalIntervalDays,
            lastReviewDate = newNextReviewDate,
            isMastered = isMastered
        )
    }
}
