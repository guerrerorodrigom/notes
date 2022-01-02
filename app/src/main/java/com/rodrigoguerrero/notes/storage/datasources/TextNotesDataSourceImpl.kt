package com.rodrigoguerrero.notes.storage.datasources

import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.storage.dao.TextNotesDao
import com.rodrigoguerrero.notes.storage.mappers.toNote
import com.rodrigoguerrero.notes.storage.mappers.toTextNoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import java.util.*
import javax.inject.Inject

class TextNotesDataSourceImpl @Inject constructor(
    private val textNotesDao: TextNotesDao
) : TextNotesDataSource {

    override suspend fun addNote(note: Note) {
        textNotesDao.insertNote(note = note.toTextNoteEntity())
    }

    override suspend fun getAllNotes(): Flow<List<Note>> {
        return textNotesDao.getAll()
            .transform {
                it.map { entity ->
                    entity.toNote()
                }
            }
    }

    override suspend fun getNote(id: UUID): Flow<Note> {
        return textNotesDao.getNote(id).map { it.toNote() }
    }
}
