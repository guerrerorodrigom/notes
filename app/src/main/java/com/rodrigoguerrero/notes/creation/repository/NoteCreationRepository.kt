package com.rodrigoguerrero.notes.creation.repository

import com.rodrigoguerrero.notes.common.models.Note
import kotlinx.coroutines.flow.SharedFlow

interface NoteCreationRepository {

    val noteCreationStatus: SharedFlow<NoteCreationStatus>

    suspend fun createTextNote(note: Note)
}