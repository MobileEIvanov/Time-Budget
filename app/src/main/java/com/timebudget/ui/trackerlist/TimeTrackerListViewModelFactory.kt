package com.timebudget.ui.trackerlist

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.timebudget.data.RepositoryTimeTracker

/**
 * Created by emil.ivanov on 9/20/18.
 */
class TimeTrackerListViewModelFactory(val repository: RepositoryTimeTracker) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimeTrackerListViewModel::class.java)) {
            return TimeTrackerListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
