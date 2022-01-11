package com.rodrigoguerrero.notes.display.repository

import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.storage.datasources.NotesDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class NoteDisplayRepositoryImpl @Inject constructor(
    private val notesDataSource: NotesDataSource
) : NoteDisplayRepository {

    private val scope: CoroutineScope = CoroutineScope(
        Dispatchers.IO + SupervisorJob()
    )

    override val availableNotes: SharedFlow<List<Note>> = notesDataSource.notes
        .shareIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(),
            replay = 1
        )

    override val archivedNotes: SharedFlow<List<Note>> = notesDataSource.archivedNotes
        .shareIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(),
            replay = 1
        )

    override val deletedNotes: SharedFlow<List<Note>> = notesDataSource.deletedNotes
        .shareIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(),
            replay = 1
        )

    override fun destroy() {
        scope.cancel()
    }
}