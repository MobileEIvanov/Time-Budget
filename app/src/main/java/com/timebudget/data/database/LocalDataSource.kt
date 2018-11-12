package com.timebudget.data.database

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import com.timebudget.entities.TimeEntry
import java.util.concurrent.Executor

/**
 * Local data source class responsible for making local database request.
 * The class uses Executor for performing work outside the main thread
 */
class LocalDataSource(
    private val trackerEntryDao: TrackerEntryDao,
    private val ioExecutor: Executor) {


    fun insert(noteEntry: TimeEntry) {
        ioExecutor.execute {
            trackerEntryDao.insert(noteEntry)
        }
    }

    fun update(noteEntry: TimeEntry) {
        ioExecutor.execute {
            trackerEntryDao.update(noteEntry)
        }
    }


    fun delete(noteEntry: TimeEntry) {
        ioExecutor.execute {
            trackerEntryDao.delete(noteEntry)
        }
    }

    fun fetchById(id: Long): LiveData<TimeEntry> {
        return trackerEntryDao.loadById(id)
    }

    fun query(): DataSource.Factory<Int, TimeEntry> {
        return trackerEntryDao.loadAll()
    }

}