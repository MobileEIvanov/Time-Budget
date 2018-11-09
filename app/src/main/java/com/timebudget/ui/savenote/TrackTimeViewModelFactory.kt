package com.timebudget.ui.savenote

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.timebudget.data.RepositoryTimeTracker

/**
 * Created by emil.ivanov on 9/9/18.
 */
class TrackTimeViewModelFactory(val repository: RepositoryTimeTracker) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrackTimeViewModel::class.java)) {
            return TrackTimeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
