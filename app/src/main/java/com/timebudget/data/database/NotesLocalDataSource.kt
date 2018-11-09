package com.timebudget.data.database

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import com.timebudget.entities.TimeTrackEntry
import java.util.concurrent.Executor

/**
 * Local data source class responsible for making local database request.
 * The class uses Executor for performing work outside the main thread
 */
class NotesLocalDataSource(
        private val noteEntryDao: NoteEntryDao,
        private val ioExecutor: Executor) {


    fun insert(noteEntry: TimeTrackEntry) {
        ioExecutor.execute {
            noteEntryDao.insertNote(noteEntry)
        }
    }

    fun update(noteEntry: TimeTrackEntry) {
        ioExecutor.execute {
            noteEntryDao.updateNote(noteEntry)
        }
    }


    fun delete(noteEntry: TimeTrackEntry) {
        ioExecutor.execute {
            noteEntryDao.deleteNote(noteEntry)
        }
    }

    fun fetchById(id: Long): LiveData<TimeTrackEntry> {
        return noteEntryDao.loadNoteById(id)
    }

    fun query(): DataSource.Factory<Int, TimeTrackEntry> {
        return noteEntryDao.loadAllNotes()
    }

}