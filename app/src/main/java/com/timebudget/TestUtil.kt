package com.timebudget

import com.timebudget.entities.IntervalType
import com.timebudget.entities.TimeEntry
import java.sql.Date

/**
 * Created by emil.ivanov on 9/21/18.
 */
fun createTimeEntry(): TimeEntry {
    return TimeEntry(
        0,
        "",
        1,
        IntervalType.HOURS.value,
        Date.valueOf(System.currentTimeMillis().toString())
    )
}
