package com.timebudget.data.database

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.*
import com.timebudget.entities.TimeEntry


/**
 * Created by emil.ivanov on 9/2/18.
 */
@Dao
interface TrackerEntryDao {

    @Query("SELECT * FROM time_tracker ORDER BY updated_at")
    fun loadAll(): DataSource.Factory<Int, TimeEntry>

    @Insert
    fun insert(timeEntry: TimeEntry)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(timeEntry: TimeEntry)

    @Delete
    fun delete(timeEntry: TimeEntry)

    @Query("SELECT * FROM time_tracker WHERE (id LIKE :id)")
    fun loadById(id: Long): LiveData<TimeEntry>

    @Query("SELECT * FROM time_tracker WHERE description LIKE :description")
    fun searchByTitle(description: String): TimeEntry
}
