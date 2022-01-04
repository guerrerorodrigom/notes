package com.rodrigoguerrero.notes.creation.repository

import com.rodrigoguerrero.notes.common.models.Note
import kotlinx.coroutines.flow.SharedFlow
import java.util.*

interface NoteCreationRepository {

    val noteOperationStatus: SharedFlow<NoteOperationStatus>

    suspend fun upsertTextNote(note: Note)

    suspend fun getNote(uuid: UUID)
}