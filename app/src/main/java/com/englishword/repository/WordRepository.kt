package com.englishword.repository

import com.englishword.data.Word
import com.englishword.data.WordDao
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    val allWords: Flow<List<Word>> = wordDao.getAllWords()

    fun getDueWords(currentDate: Long): Flow<List<Word>> {
        return wordDao.getDueWords(currentDate)
    }

    suspend fun insert(word: Word) {
        wordDao.insertWord(word)
    }

    suspend fun update(word: Word) {
        wordDao.updateWord(word)
    }

    suspend fun insertAll(words: List<Word>) {
        wordDao.insertAll(words)
    }
}
