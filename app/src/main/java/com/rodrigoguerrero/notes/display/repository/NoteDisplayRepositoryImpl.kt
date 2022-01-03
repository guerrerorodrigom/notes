package com.rodrigoguerrero.notes.display.repository

import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.storage.datasources.TextNotesDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class NoteDisplayRepositoryImpl @Inject constructor(
    private val textNotesDataSource: TextNotesDataSource
) : NoteDisplayRepository {

    private val scope: CoroutineScope = CoroutineScope(
        Dispatchers.IO + SupervisorJob()
    )

    override val availableNotes: SharedFlow<List<Note>> = textNotesDataSource.notes
        .map { list ->
            list
                .filter { note ->
                    !note.isHidden && !note.isArchived

                }
        }
        .shareIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(),
            replay = 1
        )
    override val hiddenNotes: SharedFlow<List<Note>>
        get() = TODO("Not yet implemented")
    override val archivedNotes: SharedFlow<List<Note>>
        get() = TODO("Not yet implemented")


    override fun destroy() {
        scope.cancel()
    }
}