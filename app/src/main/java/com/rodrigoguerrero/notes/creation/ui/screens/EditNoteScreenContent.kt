package com.rodrigoguerrero.notes.creation.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.rodrigoguerrero.notes.common.ui.components.FulLScreenProgress
import com.rodrigoguerrero.notes.creation.ui.components.EditNoteFields
import com.rodrigoguerrero.notes.creation.viewmodels.EditNoteViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.util.*

@FlowPreview
@ExperimentalCoroutinesApi
@Composable
fun EditNoteScreen(
    uuid: UUID,
    viewModelStoreOwner: ViewModelStoreOwner,
    onSaveCompleted: () -> Unit,
    viewModel: EditNoteViewModel = hiltViewModel(viewModelStoreOwner)
) {
    val noteToEdit by viewModel.note.collectAsState(null)
    val progress by viewModel.progress.observeAsState(true)
    val areFieldsEnabled by viewModel.areFieldsEnabled.collectAsState(false)
    val saveCompleted by viewModel.saveCompleted.collectAsState(false)
    val scope = rememberCoroutineScope()

    if (saveCompleted) {
        onSaveCompleted()
        return
    }
    noteToEdit?.let { note ->
        EditNoteFields(
            title = note.title.orEmpty(),
            onTitleChanged = { viewModel.onTitleChanged(note, it) },
            content = note.content,
            onContentChanged = { viewModel.onContentChanged(note, it) },
            readOnlyFields = !areFieldsEnabled,
            scope = scope,
        )
    } ?: viewModel.getNote(uuid)

    if (progress) {
        FulLScreenProgress()
    }
}
