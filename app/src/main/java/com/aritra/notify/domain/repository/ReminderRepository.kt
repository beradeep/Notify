package com.aritra.notify.domain.repository

import com.aritra.notify.data.dao.ReminderDao
import com.aritra.notify.domain.models.Reminder
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class ReminderRepository @Inject constructor(private val reminderDao: ReminderDao) {

    fun getAllReminder() : Flow<List<Reminder>> = reminderDao.getAllReminders()

    fun getReminderById(reminderId: Int) : Flow<Reminder> = reminderDao.getReminderById(reminderId)

    suspend fun insertNoteToRoom(reminder: Reminder) = reminderDao.insertReminder(reminder)

    suspend fun updateNoteInRoom(reminder: Reminder) = reminderDao.updateReminder(reminder)

    suspend fun deleteNoteFromRoom(reminder: Reminder) = reminderDao.deleteReminder(reminder)

}