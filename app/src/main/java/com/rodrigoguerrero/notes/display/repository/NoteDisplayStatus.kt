package com.rodrigoguerrero.notes.display.repository

import com.rodrigoguerrero.notes.common.models.Note

sealed class NoteDisplayStatus {
    data class Success(val notes: List<Note>) : NoteDisplayStatus()
    object Failure : NoteDisplayStatus()
    object Empty : NoteDisplayStatus()
    object Processing : NoteDisplayStatus()
}
