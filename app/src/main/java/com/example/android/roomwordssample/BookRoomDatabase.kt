package com.example.android.roomwordssample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Book::class], version = 1)
abstract class BookRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: BookRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): BookRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookRoomDatabase::class.java,
                    "book_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.wordDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(bookDao: BookDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            bookDao.deleteAll()

            var book = Book("Hello")
            bookDao.insert(book)
            book = Book("World!")
            bookDao.insert(book)
        }
    }
}
