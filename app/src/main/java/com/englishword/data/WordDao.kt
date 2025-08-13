package com.englishword.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: Word)

    @Update
    suspend fun updateWord(word: Word)

    @Query("SELECT * FROM words_table WHERE id = :id")
    fun getWordById(id: Int): Flow<Word>

    @Query("SELECT * FROM words_table ORDER BY english_text ASC")
    fun getAllWords(): Flow<List<Word>>

    // Query for the SRS feature: gets all words due for review
    @Query("SELECT * FROM words_table WHERE last_review_date <= :currentDate AND is_mastered = 0 ORDER BY last_review_date ASC")
    fun getDueWords(currentDate: Long): Flow<List<Word>>

    // For the import feature later
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(words: List<Word>)
}
