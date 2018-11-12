package com.timebudget.ui.trackactivity

import com.timebudget.createTimeEntry
import org.junit.Test


class TrackTimeViewTest{


    var mockView = TrackTimeView();


    @Test
    fun test_save_Invocation_and_Params(){
        // Arrange
        var timeEntry = createTimeEntry()

        mockView.saveTimeEntry(timeEntry.description, timeEntry.interval, timeEntry.intervalType)
    }




}