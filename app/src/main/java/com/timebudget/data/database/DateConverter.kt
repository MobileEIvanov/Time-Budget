package com.timebudget.data.database

import android.arch.persistence.room.TypeConverter
import java.util.*


/**
 * Created by emil.ivanov on 9/2/18.
 */
class DateConverterKt {

    @TypeConverter
    fun toDate(timestamp: Long): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date): Long? {
        return if (date == null) null else date.time
    }
}
