package com.timebudget

import com.timebudget.entities.TimeTrackEntry
import java.sql.Date

/**
 * Created by emil.ivanov on 9/21/18.
 */
fun createNote(): TimeTrackEntry {
    return TimeTrackEntry(0, "", "", 0, Date.valueOf(System.currentTimeMillis().toString()))
}
