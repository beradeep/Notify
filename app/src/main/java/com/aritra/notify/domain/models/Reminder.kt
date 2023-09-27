package com.aritra.notify.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date
import java.util.UUID

@Parcelize
@Entity(tableName = "reminder")
data class Reminder(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,
    var description: String,
    var reminderDateTime: Date?,
    var requestId: UUID,
    var createdAt: Date,
    var updatedAt: Date
) : Parcelable
