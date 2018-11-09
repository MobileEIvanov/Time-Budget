package com.timebudget.data.database

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import com.timebudget.entities.TimeTrackEntry
import java.util.concurrent.Executor

/**
 * Local data source class responsible for making local database request.
 * The class uses Executor for performing work outside the main thread
 */
class LocalDataSource(
    private val trackerEntryDao: TrackerEntryDao,
    private val ioExecutor: Executor) {


    fun insert(noteEntry: TimeTrackEntry) {
        ioExecutor.execute {
            trackerEntryDao.insert(noteEntry)
        }
    }

    fun update(noteEntry: TimeTrackEntry) {
        ioExecutor.execute {
            trackerEntryDao.update(noteEntry)
        }
    }


    fun delete(noteEntry: TimeTrackEntry) {
        ioExecutor.execute {
            trackerEntryDao.delete(noteEntry)
        }
    }

    fun fetchById(id: Long): LiveData<TimeTrackEntry> {
        return trackerEntryDao.loadById(id)
    }

    fun query(): DataSource.Factory<Int, TimeTrackEntry> {
        return trackerEntryDao.loadAll()
    }

}