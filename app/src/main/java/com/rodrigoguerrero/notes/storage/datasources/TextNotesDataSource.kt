package com.rodrigoguerrero.notes.storage.datasources

import com.rodrigoguerrero.notes.common.models.Note
import kotlinx.coroutines.flow.Flow

interface TextNotesDataSource {

    val notes: Flow<List<Note>>

    suspend fun addNote(note: Note)
}
