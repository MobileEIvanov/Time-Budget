package com.timebudget.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*


/**
 * TimeTrackEntry presentation
 */
@Entity(tableName = "tracker")
data class TimeTrackEntry(@PrimaryKey(autoGenerate = true) val id: Long,
                          val title: String,
                          val description: String,
                          val priority: Int,
                          @ColumnInfo(name = "updated_at") val updatedAt: Date?) {

    override fun toString(): String {
        return "TimeTrackEntry(id=$id, title='$title', description='$description', priority=$priority, updatedAt=$updatedAt)"
    }

    companion object {
        const val DATA = "note_data"
        const val DEFAULT_ID: Long = 0
    }
}