package com.timebudget.ui.savenote

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.timebudget.data.RepositoryTimeTracker
import com.timebudget.entities.TimeTrackEntry

/**
 * Created by emil.ivanov on 9/9/18.
 */
class TrackTimeViewModel(val repository: RepositoryTimeTracker) : ViewModel() {

    var noteEntry: LiveData<TimeTrackEntry> = MutableLiveData<TimeTrackEntry>()


    fun getNoteEntry(id: Long): LiveData<TimeTrackEntry> {
        if (this.noteEntry == null) {
            this.noteEntry = repository.fetchById(id)
        }
        return this.noteEntry
    }

    fun insert(noteEntry: TimeTrackEntry) {
        repository.insert(noteEntry)
    }

    fun update(noteEntry: TimeTrackEntry) {
        repository.update(noteEntry)
    }
}
