package com.rodrigoguerrero.notes.creation.ui.screens

import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.primarySurface
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.common.ui.components.FulLScreenProgress
import com.rodrigoguerrero.notes.common.ui.components.MainScaffold
import com.rodrigoguerrero.notes.common.ui.components.NotesAlertDialog
import com.rodrigoguerrero.notes.common.ui.topBarElevation
import com.rodrigoguerrero.notes.creation.ui.components.EditNoteBackNavIcon
import com.rodrigoguerrero.notes.creation.ui.components.EditNoteFab
import com.rodrigoguerrero.notes.creation.ui.components.EditNoteFields
import com.rodrigoguerrero.notes.creation.ui.components.EditNoteTopAppBarActions
import com.rodrigoguerrero.notes.creation.viewmodels.EditNoteViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.util.*

@FlowPreview
@ExperimentalCoroutinesApi
@Composable
fun EditNoteScreen(
    uuid: UUID,
    onBackPressed: () -> Unit
) {
    val viewModel: EditNoteViewModel = hiltViewModel()
    val hasChanged by viewModel.hasChanged.collectAsState(false)
    val noteToEdit by viewModel.note.collectAsState(null)
    val progress by viewModel.progress.observeAsState(true)
    val areFieldsEnabled by viewModel.areFieldsEnabled.collectAsState(false)
    val saveCompleted by viewModel.saveCompleted.collectAsState(false)
    val showWarning by viewModel.showWarning.collectAsState(false)
    val scope = rememberCoroutineScope()
    val focusRequester = FocusRequester()

    MainScaffold(
        topBar = {
            TopAppBar(
                elevation = topBarElevation,
                backgroundColor = MaterialTheme.colors.primarySurface,
                navigationIcon = {
                    EditNoteBackNavIcon(
                        hasChanged = hasChanged,
                        onShowWarning = { viewModel.showWarning() },
                        onBackPressed = onBackPressed
                    )
                },
                actions = { EditNoteTopAppBarActions() },
                title = {}
            )
        },
        fab = {
            EditNoteFab(
                areFieldsEnabled = areFieldsEnabled,
                onSaveClicked = { viewModel.save() },
                onEditClicked = {
                    focusRequester.requestFocus()
                    viewModel.enableFields()
                }
            )
        }
    ) {
        when {
            saveCompleted -> onBackPressed()
            progress -> {
                FulLScreenProgress()
                viewModel.getNote(uuid)
            }
            else -> {
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
                        focusRequester = focusRequester
                    )
                }
            }
        }
    }
}
