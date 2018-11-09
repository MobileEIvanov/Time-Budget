package com.timebudget.ui.noteslist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.timebudget.data.RepositoryTimeTracker
import com.timebudget.entities.TimeTrackEntry

/**
 * Created by emil.ivanov on 9/9/18.
 */
class TimeTrackerListViewModel(val repository: RepositoryTimeTracker) : ViewModel() {
    fun getNotes(): LiveData<PagedList<TimeTrackEntry>> {
        return repository.fetch().data
    }

    fun delete(noteEntry: TimeTrackEntry) {
        repository.delete(noteEntry)
    }

}
