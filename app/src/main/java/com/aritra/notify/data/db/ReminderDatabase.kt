package com.aritra.notify.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aritra.notify.data.converters.Converters
import com.aritra.notify.data.dao.ReminderDao
import com.aritra.notify.domain.models.Reminder

@Database(entities = [Reminder::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ReminderDatabase : RoomDatabase() {

    abstract fun reminderDao() : ReminderDao
}