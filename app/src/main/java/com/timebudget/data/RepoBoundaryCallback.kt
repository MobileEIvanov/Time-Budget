package com.timebudget.data

import android.arch.paging.PagedList
import android.util.Log
import com.timebudget.data.database.NotesLocalDataSource
import com.timebudget.entities.TimeTrackEntry

/**
 * Created by emil.ivanov on 9/19/18.
 * Reference https://codelabs.developers.google.com/codelabs/android-paging/index.html?index=..%2F..%2Findex#8
 * Callback responsible to notify when the local data source has run out of items.
 * On such occasion if there is a remote repository we load the items from network and insert them in DB
 *
 */
class RepoBoundaryCallback(localDataSource: NotesLocalDataSource) : PagedList.BoundaryCallback<TimeTrackEntry>() {

    override fun onZeroItemsLoaded() {
        Log.d("Boundary", "onZeroItemsLoaded")
    }

    override fun onItemAtEndLoaded(itemAtEnd: TimeTrackEntry) {
        Log.d("Boundary", "onItemAtEndLoaded:" + itemAtEnd.title)
    }

}