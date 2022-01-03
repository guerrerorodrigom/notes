package com.rodrigoguerrero.notes.storage.datasources

import com.rodrigoguerrero.notes.common.models.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import java.util.*

interface TextNotesDataSource {

    val notes: Flow<List<Note>>

    suspend fun addNote(note: Note)

    suspend fun getNote(id: UUID): Flow<Note>
}
