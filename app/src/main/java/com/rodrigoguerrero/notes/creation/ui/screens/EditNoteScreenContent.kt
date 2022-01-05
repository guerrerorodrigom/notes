package com.rodrigoguerrero.notes.creation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.common.ui.components.FulLScreenProgress
import com.rodrigoguerrero.notes.common.ui.components.NotesAlertDialog
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
    val showWarning by viewModel.showWarning.collectAsState(false)
    val scope = rememberCoroutineScope()

    if (saveCompleted) {
        onSaveCompleted()
        return
    }

    if (showWarning) {
        NotesAlertDialog(
            confirmText = R.string.ok,
            dismissText = R.string.cancel,
            title = R.string.unsaved_note,
            content = R.string.unsaved_note_content,
            onDismiss = { viewModel.hideWarning() },
            onConfirm = {
                viewModel.save()
                viewModel.hideWarning()
            },
            onDismissRequest = {
                viewModel.hideWarning()
            })
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
