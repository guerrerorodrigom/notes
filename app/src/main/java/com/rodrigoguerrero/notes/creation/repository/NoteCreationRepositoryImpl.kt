package com.rodrigoguerrero.notes.creation.repository

import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.storage.datasources.NotesDataSource
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject

@FlowPreview
class NoteCreationRepositoryImpl @Inject constructor(
    private val notesDataSource: NotesDataSource
) : NoteCreationRepository {

    private val _status = MutableSharedFlow<NoteOperationStatus>()
    override val noteOperationStatus: SharedFlow<NoteOperationStatus>
        get() = _status

    override suspend fun upsertTextNote(note: Note) {
        _status.emit(NoteOperationStatus.Processing)
        notesDataSource.addNote(note)
        _status.emit(NoteOperationStatus.Success)
    }

    override suspend fun getNote(uuid: UUID) {
        _status.emit(NoteOperationStatus.Processing)
        notesDataSource
            .getNote(uuid)
            .collect { note ->
                _status.emit(NoteOperationStatus.Retrieved(note))
            }
    }
}