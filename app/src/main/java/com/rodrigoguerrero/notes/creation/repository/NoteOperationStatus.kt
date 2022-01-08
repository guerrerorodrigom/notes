package com.rodrigoguerrero.notes.creation.repository

import com.rodrigoguerrero.notes.common.models.Note

sealed class NoteOperationStatus {
    object Success : NoteOperationStatus()
    data class Retrieved(val note: Note) : NoteOperationStatus()
    object Processing : NoteOperationStatus()
    object Failure : NoteOperationStatus()
    object Archived : NoteOperationStatus()
    object Unarchived : NoteOperationStatus()
}
