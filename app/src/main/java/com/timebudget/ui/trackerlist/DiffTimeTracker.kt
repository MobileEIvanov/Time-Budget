package com.timebudget.ui.trackerlist

import android.support.v7.util.DiffUtil
import com.timebudget.entities.TimeEntry
import java.util.List

/**
 * Created by emil.ivanov on 9/8/18.
 */
class DiffTimeTracker(private val notesOld: List<TimeEntry>, private val newNotes: List<TimeEntry>) : DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return if (notesOld != null) notesOld.size else 0
    }

    @Override
    override fun getNewListSize(): Int {
        return if (newNotes != null) newNotes.size else 0
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return notesOld[oldItemPosition].interval == newNotes[newItemPosition].interval
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return notesOld[oldItemPosition] == newNotes[newItemPosition]
    }

}