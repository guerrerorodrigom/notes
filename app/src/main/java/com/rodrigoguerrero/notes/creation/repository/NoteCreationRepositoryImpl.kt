package com.rodrigoguerrero.notes.creation.repository

import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.storage.datasources.TextNotesDataSource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class NoteCreationRepositoryImpl @Inject constructor(
    private val textNotesDataSource: TextNotesDataSource
) : NoteCreationRepository {

    private val _noteCreationStatus = MutableSharedFlow<NoteCreationStatus>()
    override val noteCreationStatus: SharedFlow<NoteCreationStatus>
        get() = _noteCreationStatus

    override suspend fun createTextNote(note: Note) {
        _noteCreationStatus.emit(NoteCreationStatus.Processing)
        textNotesDataSource.addNote(note)
        _noteCreationStatus.emit(NoteCreationStatus.Success)
    }
}