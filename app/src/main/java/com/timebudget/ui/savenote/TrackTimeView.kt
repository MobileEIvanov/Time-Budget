package com.timebudget.ui.savenote

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
import com.timebudget.entities.TimeTrackEntry
import com.timebudget.utils.isValidTextInput
import kotlinx.android.synthetic.main.layout_create_note.view.*
import java.util.*

class TrackTimeView : BottomSheetDialogFragment() {


    private var factory: TrackTimeViewModelFactory? = null
    private var viewModel: TrackTimeViewModel? = null
    private var layout: View? = null
    private var noteId: Long = TimeTrackEntry.DEFAULT_ID


    private val listenerSave = View.OnClickListener {

        val title = layout!!.inputNoteTitle.text.toString()
        val description = layout!!.inputNoteDesc.text.toString()

        if (isValidTextInput(title) &&
                isValidTextInput(description)) {

            saveNote(title, description)
        }
    }

    private fun saveNote(title: String, description: String) {
        val timeNow = GregorianCalendar.getInstance(TimeZone.getDefault()).time;
        if (noteId != TimeTrackEntry.DEFAULT_ID) {
            viewModel!!.update(TimeTrackEntry(noteId, title, description, 0, timeNow))
        } else {
            viewModel!!.insert(TimeTrackEntry(noteId, title, description, 0, timeNow))
        }
        dismiss()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(DialogFragment.STYLE_NO_FRAME, 0)
        noteId = arguments!!.getLong(TimeTrackEntry.DATA)

        viewModel = ViewModelProviders.of(this, Injection.provideSaveNoteViewModelFactory(activity!!)).get(TrackTimeViewModel::class.java)

        viewModel?.getNoteEntry(noteId)?.observe(this, Observer { populateData(it) })
    }

    private fun populateData(noteEntry: TimeTrackEntry?) {
        view?.inputNoteTitle?.setText(noteEntry?.title)
        view?.inputNoteDesc?.setText(noteEntry?.description)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        layout = inflater.inflate(R.layout.layout_create_note, container, false)
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
                        putLong(TimeTrackEntry.DATA, noteId)
                    }
                }
    }
}
