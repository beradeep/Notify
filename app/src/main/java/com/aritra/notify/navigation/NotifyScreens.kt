package com.aritra.notify.navigation

sealed class NotifyScreens(val name: String) {

    object Notes : NotifyScreens("notes")
    object AddNotes : NotifyScreens("add_note")
    object UpdateNotes : NotifyScreens("update_note")
    object Reminder : NotifyScreens("reminder")
    object AddReminder : NotifyScreens("add_reminder")
    object UpdateReminder : NotifyScreens("update_reminder")
    object Settings : NotifyScreens("setting")
}
