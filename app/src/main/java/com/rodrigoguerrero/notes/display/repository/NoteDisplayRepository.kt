package com.rodrigoguerrero.notes.display.repository

import com.rodrigoguerrero.notes.common.models.Note
import kotlinx.coroutines.flow.SharedFlow

interface NoteDisplayRepository {
    val allNotes: SharedFlow<List<Note>>

    fun destroy()
}
