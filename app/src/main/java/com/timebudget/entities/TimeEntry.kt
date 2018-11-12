package com.timebudget.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*


/**
 * TimeEntry presentation
 */
@Entity(tableName = "time_tracker")
data class TimeEntry(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val description: String,
    val interval: Double,
    val intervalType: String,
    @ColumnInfo(name = "updated_at") val updatedAt: Date?
) {


    override fun toString(): String {
        return "TimeEntry(id=$id, description='$description', interval=$interval, intervalType='$intervalType', updatedAt=$updatedAt)"
    }

    companion object {
        const val DATA = "tracker_data"
        const val DEFAULT_ID: Long = 0
    }

}