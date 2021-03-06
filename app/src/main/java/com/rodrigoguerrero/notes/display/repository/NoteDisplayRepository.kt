package com.rodrigoguerrero.notes.display.repository

import com.rodrigoguerrero.notes.common.models.Note
import kotlinx.coroutines.flow.SharedFlow

interface NoteDisplayRepository {
    val availableNotes: SharedFlow<List<Note>>
    val archivedNotes: SharedFlow<List<Note>>
    val deletedNotes: SharedFlow<List<Note>>
    val pinnedNotes: SharedFlow<List<Note>>

    fun destroy()
}
