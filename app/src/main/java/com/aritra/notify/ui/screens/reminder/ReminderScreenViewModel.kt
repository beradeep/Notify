package com.aritra.notify.ui.screens.reminder

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import com.aritra.notify.domain.repository.ReminderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReminderScreenViewModel @Inject constructor(
    application: Application,
    repository: ReminderRepository
) : AndroidViewModel(application) {

    var listOfReminder = repository.getAllReminder().asLiveData()

}