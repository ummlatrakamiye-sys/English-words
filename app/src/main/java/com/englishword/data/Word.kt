package com.englishword.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words_table")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "english_text")
    val englishText: String,

    @ColumnInfo(name = "arabic_text")
    val arabicText: String,

    @ColumnInfo(name = "last_review_date")
    val lastReviewDate: Long, // Using Long for timestamp

    @ColumnInfo(name = "repetition_interval")
    val repetitionInterval: Int,

    @ColumnInfo(name = "repetition_count")
    val repetitionCount: Int,

    @ColumnInfo(name = "is_mastered")
    val isMastered: Boolean,

    @ColumnInfo(name = "category")
    val category: String? = null // Optional as specified
)
