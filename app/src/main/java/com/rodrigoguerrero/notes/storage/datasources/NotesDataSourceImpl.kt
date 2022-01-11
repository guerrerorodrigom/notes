package com.rodrigoguerrero.notes.storage.datasources

import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.storage.dao.NotesDao
import com.rodrigoguerrero.notes.storage.mappers.toNote
import com.rodrigoguerrero.notes.storage.mappers.toTextNoteEntity
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject

class NotesDataSourceImpl @Inject constructor(
    private val notesDao: NotesDao
) : NotesDataSource {
    override val notes: Flow<List<Note>> = notesDao
        .getAvailable()
        .map { list ->
            list.map { entity ->
                entity.toNote()
            }
        }

    override val archivedNotes: Flow<List<Note>> = notesDao
        .getArchived()
        .map { list ->
            list.map { entity ->
                entity.toNote()
            }
        }

    override val deletedNotes: Flow<List<Note>> = notesDao
        .getDeleted()
        .map { list ->
            list.map { entity ->
                entity.toNote()
            }
        }

    override suspend fun addNote(note: Note) {
        notesDao.insertNote(note = note.toTextNoteEntity())
    }

    override suspend fun getNote(uuid: UUID): Flow<Note> {
        return notesDao.getNote(uuid).map { it.toNote() }
    }
}
