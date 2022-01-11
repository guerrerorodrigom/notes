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
        .getAvailable()
        .map { list ->
            list.map { entity ->
                entity.toNote()
            }
        }

    override val archivedNotes: Flow<List<Note>> = textNotesDao
        .getArchived()
        .map { list ->
            list.map { entity ->
                entity.toNote()
            }
        }

    override val deletedNotes: Flow<List<Note>> = textNotesDao
        .getDeleted()
        .map { list ->
            list.map { entity ->
                entity.toNote()
            }
        }

    override suspend fun addNote(note: Note) {
        textNotesDao.insertNote(note = note.toTextNoteEntity())
    }

    override suspend fun getNote(uuid: UUID): Flow<Note> {
        return textNotesDao.getNote(uuid).map { it.toNote() }
    }
}
