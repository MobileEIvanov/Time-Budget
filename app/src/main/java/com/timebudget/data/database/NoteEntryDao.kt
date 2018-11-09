package com.timebudget.data.database

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.*
import com.timebudget.entities.TimeTrackEntry


/**
 * Created by emil.ivanov on 9/2/18.
 */
@Dao
interface NoteEntryDao {

    @Query("SELECT * FROM note ORDER BY priority")
    fun loadAllNotes(): DataSource.Factory<Int, TimeTrackEntry>

    @Insert
    fun insertNote(noteEntry: TimeTrackEntry)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNote(noteEntry: TimeTrackEntry)

    @Delete
    fun deleteNote(noteEntry: TimeTrackEntry)

    @Query("SELECT * FROM note WHERE (id LIKE :noteId)")
    fun loadNoteById(noteId: Long): LiveData<TimeTrackEntry>

    @Query("SELECT * FROM note WHERE title LIKE :noteTitle")
    fun searchByTitle(noteTitle: String): TimeTrackEntry
}
