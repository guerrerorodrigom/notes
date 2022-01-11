package com.rodrigoguerrero.notes.creation.repository

import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.storage.datasources.TextNotesDataSource
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject

@FlowPreview
class NoteCreationRepositoryImpl @Inject constructor(
    private val textNotesDataSource: TextNotesDataSource
) : NoteCreationRepository {

    private val _status = MutableSharedFlow<NoteOperationStatus>()
    override val noteOperationStatus: SharedFlow<NoteOperationStatus>
        get() = _status

    override suspend fun upsertTextNote(note: Note) {
        _status.emit(NoteOperationStatus.Processing)
        textNotesDataSource.addNote(note)
        _status.emit(NoteOperationStatus.Success)
    }

    override suspend fun toggleArchiveState(note: Note) {
        textNotesDataSource.addNote(note.copy(isArchived = !note.isArchived))
        val status = if (note.isArchived) {
            NoteOperationStatus.Unarchived
        } else {
            NoteOperationStatus.Archived
        }
        _status.emit(status)
    }

    override suspend fun getNote(uuid: UUID) {
        _status.emit(NoteOperationStatus.Processing)
        textNotesDataSource
            .getNote(uuid)
            .collect { note ->
                _status.emit(NoteOperationStatus.Retrieved(note))
            }
    }
}