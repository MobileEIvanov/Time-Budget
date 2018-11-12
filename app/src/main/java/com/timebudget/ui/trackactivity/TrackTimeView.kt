package com.timebudget.ui.trackactivity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.timebudget.Injection
import com.timebudget.R
import com.timebudget.entities.IntervalType
import com.timebudget.entities.TimeEntry
import kotlinx.android.synthetic.main.layout_save_time.*
import kotlinx.android.synthetic.main.layout_save_time.view.*
import java.util.*

class TrackTimeView : BottomSheetDialogFragment() {


    private var factory: TrackTimeViewModelFactory? = null
    private var viewModel: TrackTimeViewModel? = null
    private var layout: View? = null
    private var noteId: Long = TimeEntry.DEFAULT_ID


    private val listenerSave = View.OnClickListener {

        val interval = layout!!.interval.text.toString().toDouble()
        val description = layout!!.inputNoteDesc.text.toString()

        val intervalType = getIntervalType()

        saveTimeEntry(description, interval, intervalType)
    }

    fun getIntervalType(): String {

        if (btnHours.isSelected) {
            return IntervalType.HOURS.value
        } else {
            return IntervalType.MIN.value
        }
    }

    fun saveTimeEntry(description: String, interval: Double, intervalType: String) {
        val timeNow = GregorianCalendar.getInstance(TimeZone.getDefault()).time;
        if (noteId != TimeEntry.DEFAULT_ID) {
            viewModel!!.update(TimeEntry(noteId, description, interval, intervalType, timeNow))
        } else {
            viewModel!!.insert(TimeEntry(noteId, description, interval, intervalType, timeNow))
        }
        dismiss()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(DialogFragment.STYLE_NO_FRAME, 0)
        noteId = arguments!!.getLong(TimeEntry.DATA)

        viewModel =
                ViewModelProviders.of(this, Injection.provideSaveNoteViewModelFactory(activity!!))
                    .get(TrackTimeViewModel::class.java)

        viewModel?.getNoteEntry(noteId)?.observe(this, Observer { populateData(it) })
    }

    fun populateData(timeEntry: TimeEntry?) {
        view?.inputNoteDesc?.setText(timeEntry?.description)
        view?.interval?.setText(timeEntry?.interval.toString())

        when (timeEntry?.intervalType) {
            IntervalType.HOURS.value -> {
                view?.btnHours?.isSelected = true
            }

            IntervalType.MIN.value -> {
                view?.btnMinutes?.isSelected = true
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.layout_save_time, container, false)
        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layout?.btnSave?.setOnClickListener(listenerSave)
    }


    companion object {
        val TAG = "save_time_entry"
        fun newInstance(noteId: Long): TrackTimeView =
            TrackTimeView().apply {
                arguments = Bundle().apply {
                    putLong(TimeEntry.DATA, noteId)
                }
            }
    }
}
