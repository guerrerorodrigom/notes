package com.rodrigoguerrero.notes.creation.models

data class ReminderSuggestion(
    val date: Long,
    val description: String,
    val time: String = ""
)
