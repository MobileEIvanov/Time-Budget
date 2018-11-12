package com.timebudget.ui.trackactivity

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.timebudget.data.RepositoryTimeTracker
import com.timebudget.entities.TimeEntry

/**
 * Created by emil.ivanov on 9/9/18.
 */
class TrackTimeViewModel(val repository: RepositoryTimeTracker) : ViewModel() {

    var noteEntry: LiveData<TimeEntry> = MutableLiveData<TimeEntry>()

    var viewState: MutableLiveData<TrackUiModel> = MutableLiveData();


    fun getNoteEntry(id: Long): LiveData<TimeEntry> {
        if (this.noteEntry == null) {
            this.noteEntry = repository.fetchById(id)
        }
        return this.noteEntry
    }

    fun insert(noteEntry: TimeEntry) {
        repository.insert(noteEntry)
    }

    fun update(noteEntry: TimeEntry) {
        repository.update(noteEntry)
    }
}
