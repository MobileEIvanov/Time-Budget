package com.timebudget.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.timebudget.entities.TimeTrackEntry

/**
 *
 */
@Database(entities = [TimeTrackEntry::class], version = 1, exportSchema = false)
@TypeConverters(DateConverterKt::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): TrackerEntryDao

    companion object {
        const val DATABASE_NAME = "time_budget"

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }


}
