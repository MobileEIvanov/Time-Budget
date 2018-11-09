package com.timebudget.data.database

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.*
import com.timebudget.entities.TimeTrackEntry


/**
 * Created by emil.ivanov on 9/2/18.
 */
@Dao
interface TrackerEntryDao {

    @Query("SELECT * FROM tracker ORDER BY updated_at")
    fun loadAll(): DataSource.Factory<Int, TimeTrackEntry>

    @Insert
    fun insert(timeEntry: TimeTrackEntry)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(timeEntry: TimeTrackEntry)

    @Delete
    fun delete(timeEntry: TimeTrackEntry)

    @Query("SELECT * FROM tracker WHERE (id LIKE :id)")
    fun loadById(id: Long): LiveData<TimeTrackEntry>

    @Query("SELECT * FROM tracker WHERE title LIKE :title")
    fun searchByTitle(title: String): TimeTrackEntry
}
