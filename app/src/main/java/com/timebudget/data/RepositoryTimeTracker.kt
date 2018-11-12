package com.timebudget.data

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import com.timebudget.data.database.LocalDataSource
import com.timebudget.entities.TimeEntry
import com.timebudget.entities.ResponseTimeTrackerRepo

/**
 * Created by emil.ivanov on 9/18/18.
 */
class RepositoryTimeTracker(private val localDataSource: LocalDataSource) {


    fun insert(timeTracker: TimeEntry) {
        localDataSource.insert(timeTracker)
    }


    fun update(timeTracker: TimeEntry) {
        localDataSource.update(timeTracker)
    }


    fun delete(timeTracker: TimeEntry) {
        localDataSource.delete(timeTracker)
    }

    fun fetch(): ResponseTimeTrackerRepo {
        // Get the data source factory from the local source;
        val dataSourceFactory = localDataSource.query()

        // Get the paged list
        val pagedList = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(RepoBoundaryCallback(localDataSource))
                .build()


        return ResponseTimeTrackerRepo(pagedList)
    }


    fun fetchById(id: Long): LiveData<TimeEntry> {
        return localDataSource.fetchById(id)
    }


    companion object {
        private const val DATABASE_PAGE_SIZE = 3
    }
}