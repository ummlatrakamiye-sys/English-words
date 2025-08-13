package com.englishword

import android.app.Application
import com.englishword.data.AppDatabase
import com.englishword.repository.WordRepository

class EnglishWordApplication : Application() {
    // Using by lazy so the database and repository are only created when they're needed
    // rather than when the application starts.
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { WordRepository(database.wordDao()) }
}
