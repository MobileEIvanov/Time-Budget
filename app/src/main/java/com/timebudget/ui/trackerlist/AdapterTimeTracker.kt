package com.timebudget.ui.trackerlist

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.timebudget.R
import com.timebudget.entities.TimeEntry
import com.timebudget.utils.inflate
import kotlinx.android.synthetic.main.item_list_time_tracker.view.*

/**
 * Created by emil.ivanov on 9/8/18.
 *
 * Implementation for click listener reference:
 * https://www.andreasjakl.com/recyclerview-kotlin-style-click-listener-android/
 */
class AdapterTimeTracker
constructor(private val clickListener: (TimeEntry) -> Unit,
            private val deleteNoteListener: (TimeEntry) -> Unit)
    : PagedListAdapter<TimeEntry, AdapterTimeTracker.VHTimeTracker>(TIME_TRACKER_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHTimeTracker {
        val inflatedView = parent.inflate(R.layout.item_list_time_tracker, false)
        return VHTimeTracker(inflatedView)
    }

    override fun onBindViewHolder(holder: VHTimeTracker, position: Int) {
        val noteItem = getItem(position)
        holder.bindData(noteItem, clickListener, deleteNoteListener)
        holder.itemView.tag = noteItem
    }


    class VHTimeTracker(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private var view: View = itemView
        private var timeEntry: TimeEntry? = null


        fun bindData(note: TimeEntry?, clickListener: (TimeEntry) -> Unit, deleteNoteListener: (TimeEntry) -> Unit) {
            if (note != null) {
                this.timeEntry = note
                view.tvTitle.text = note.interval
                view.tvDescription.text = note.description
                view.btnDelete.setOnClickListener { deleteNoteListener(note) }
                view.setOnClickListener { clickListener(note) }
            }
        }
    }


    companion object {
        private val TIME_TRACKER_COMPARATOR = object : DiffUtil.ItemCallback<TimeEntry>() {
            override fun areItemsTheSame(oldItem: TimeEntry, newItem: TimeEntry): Boolean =
                    oldItem.interval == newItem.interval

            override fun areContentsTheSame(oldItem: TimeEntry, newItem: TimeEntry): Boolean =
                    oldItem == newItem
        }
    }


}