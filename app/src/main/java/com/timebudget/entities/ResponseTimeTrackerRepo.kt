package com.timebudget.entities

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList

/**
 * Created by emil.ivanov on 9/19/18.
 */
data class ResponseTimeTrackerRepo(val data: LiveData<PagedList<TimeEntry>>)