package com.example.roombook

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class BooksApplication:Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { BookRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { BookRepository(database.bookDao()) }
}