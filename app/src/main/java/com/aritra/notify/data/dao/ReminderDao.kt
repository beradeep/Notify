package com.aritra.notify.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aritra.notify.domain.models.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {

    @Query("SELECT * FROM reminder ORDER BY id ASC")
    fun getAllReminders(): Flow<List<Reminder>>

    @Query("SELECT * FROM reminder WHERE id = :reminderId")
    fun getReminderById(reminderId: Int): Flow<Reminder>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertReminder(reminderModel: Reminder)

    @Update
    suspend fun updateReminder(reminderModel: Reminder)

    @Delete
    suspend fun deleteReminder(reminderModel: Reminder)
}