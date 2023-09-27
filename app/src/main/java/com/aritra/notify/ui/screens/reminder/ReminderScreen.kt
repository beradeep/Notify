package com.aritra.notify.ui.screens.reminder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aritra.notify.R
import com.aritra.notify.components.cards.ReminderCards
import com.aritra.notify.ui.screens.notes.homeScreen.NoList

@Composable
fun ReminderScreen(
    onFabClicked: () -> Unit
) {

    val reminderViewModel = hiltViewModel<ReminderScreenViewModel>()
    val listOfAllReminders by reminderViewModel.listOfReminder.observeAsState(listOf())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onFabClicked()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "Add Reminder"
                )
            }
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                if (listOfAllReminders.isNotEmpty()) {

                    LazyColumn(modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp, 5.dp, 0.dp, 0.dp)
                    ) {
                        items(listOfAllReminders){reminderModel ->
                            ReminderCards(reminderModel = reminderModel)
                        }
                    }

                } else {
                    NoList(
                        contentDescription = "No Reminders",
                        message = stringResource(R.string.tap_to_create_reminders)
                    )
                }
            }
        }
    }
}