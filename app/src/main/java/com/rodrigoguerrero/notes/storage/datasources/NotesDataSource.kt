package com.rodrigoguerrero.notes.storage.datasources

import com.rodrigoguerrero.notes.common.models.Note
import kotlinx.coroutines.flow.Flow
import java.util.*

interface NotesDataSource {

    val notes: Flow<List<Note>>
    val deletedNotes: Flow<List<Note>>
    val archivedNotes: Flow<List<Note>>
    val pinnedNotes: Flow<List<Note>>

    suspend fun addNote(note: Note)

    suspend fun getNote(uuid: UUID): Flow<Note>
}
