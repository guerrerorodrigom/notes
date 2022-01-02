package com.rodrigoguerrero.notes.storage.datasources

import com.rodrigoguerrero.notes.common.models.Note
import kotlinx.coroutines.flow.Flow
import java.util.*

interface TextNotesDataSource {

    suspend fun addNote(note: Note)

    suspend fun getAllNotes(): Flow<List<Note>>

    suspend fun getNote(id: UUID): Flow<Note>
}
