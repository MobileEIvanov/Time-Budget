package com.timebudget

import com.timebudget.entities.IntervalType
import com.timebudget.entities.TimeEntry
import java.util.*


/**
 * Created by emil.ivanov on 9/21/18.
 */
fun createTimeEntry(): TimeEntry {
    return TimeEntry(
        0,
        "",
        1.0,
        IntervalType.HOURS.value,
        GregorianCalendar.getInstance(TimeZone.getDefault()).time
    )
}
