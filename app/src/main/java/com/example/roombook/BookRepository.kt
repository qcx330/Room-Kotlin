package com.example.roombook

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao) {
    val allBooks: Flow<List<Book>> = bookDao.getAlphabetizedWords()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(book: Book) {
        bookDao.insert(book)
    }
    suspend fun update(book: Book) {
            bookDao.update(book)
    }

    suspend fun delete(book: Book) {
            bookDao.delete(book)
    }
}