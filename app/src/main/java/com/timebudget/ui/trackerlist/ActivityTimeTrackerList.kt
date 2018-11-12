package com.timebudget.ui.trackerlist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.facebook.stetho.Stetho
import com.timebudget.Injection
import com.timebudget.R
import com.timebudget.entities.TimeEntry
import com.timebudget.ui.trackactivity.TrackTimeView
import kotlinx.android.synthetic.main.activity_main.*

class ActivityTimeTrackerList : AppCompatActivity() {


    var viewModel: TimeTrackerListViewModel? = null
    private lateinit var adapter: AdapterTimeTracker
    var listenerAddNote = View.OnClickListener {

        showSaveDialog(TimeEntry.DEFAULT_ID)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Stetho.initializeWithDefaults(this);
        initialize()
        loadData()
    }


    private fun initialize() {
        viewModel = ViewModelProviders.of(this, Injection.provideNotesListViewModelFactory(this))
                .get(TimeTrackerListViewModel::class.java)
        adapter = AdapterTimeTracker(this::onNoteClickInteraction, this::onNoteDeleteInteraction)
        rvList.adapter = adapter

        bntAdd.setOnClickListener(listenerAddNote)
    }

    private fun loadData() {
        viewModel?.getNotes()?.observe(this, Observer {

            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            emptyList.visibility = View.VISIBLE
            rvList.visibility = View.GONE
        } else {
            emptyList.visibility = View.GONE
            rvList.visibility = View.VISIBLE
        }
    }


    private fun onNoteClickInteraction(noteEntry: TimeEntry) {
        showSaveDialog(noteEntry.id)
    }


    private fun onNoteDeleteInteraction(noteEntry: TimeEntry) {
        viewModel?.delete(noteEntry)
    }

    private fun showSaveDialog(noteId: Long) {
        var dialog: Fragment? = supportFragmentManager.findFragmentByTag(TrackTimeView.TAG)
        if (dialog == null) {
            dialog = TrackTimeView.newInstance(noteId)
        }
        if (!dialog.isVisible) {
            (dialog as TrackTimeView).show(supportFragmentManager, TrackTimeView.TAG)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel!!.getNotes().removeObservers(this)
    }

}
