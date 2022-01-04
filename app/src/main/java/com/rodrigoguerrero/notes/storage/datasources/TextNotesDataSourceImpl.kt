package com.rodrigoguerrero.notes.storage.datasources

import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.storage.dao.TextNotesDao
import com.rodrigoguerrero.notes.storage.mappers.toNote
import com.rodrigoguerrero.notes.storage.mappers.toTextNoteEntity
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject

class TextNotesDataSourceImpl @Inject constructor(
    private val textNotesDao: TextNotesDao
) : TextNotesDataSource {
    override val notes: Flow<List<Note>> = textNotesDao
        .getAll()
        .map { list ->
            list.map { entity ->
                entity.toNote()
            }
        }

    override suspend fun addNote(note: Note) {
        textNotesDao.insertNote(note = note.toTextNoteEntity())
    }
}
