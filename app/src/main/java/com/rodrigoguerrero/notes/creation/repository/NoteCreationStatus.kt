package com.rodrigoguerrero.notes.creation.repository

sealed class NoteCreationStatus {
    object Success : NoteCreationStatus()
    object Processing : NoteCreationStatus()
    object Failure : NoteCreationStatus()
}
