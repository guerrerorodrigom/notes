package com.rodrigoguerrero.notes.creation.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.common.ui.components.BackNavigationIcon
import com.rodrigoguerrero.notes.common.ui.components.FulLScreenProgress
import com.rodrigoguerrero.notes.common.ui.components.MainScaffold
import com.rodrigoguerrero.notes.common.ui.topBarElevation
import com.rodrigoguerrero.notes.creation.repository.NoteOperationStatus
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
    uuid: UUID?,
    onBackPressed: () -> Unit
) {
    val viewModel: EditNoteViewModel = hiltViewModel()
    val noteToEdit by viewModel.note.collectAsState(null)
    val progress by viewModel.progress.observeAsState(true)
    val saveCompleted by viewModel.saveCompleted.collectAsState(false)
    val archiveIcon by viewModel.archiveIcon.collectAsState(Icons.Filled.Archive)
    val noteArchivedStatus by viewModel.archiveNote.collectAsState(null)
    val snackbarHostState = remember { SnackbarHostState() }
    val scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState)
    val archivedMessage = stringResource(R.string.note_archived)
    val unarchivedMessage = stringResource(R.string.note_unarchived)
    val undoMessage = stringResource(R.string.undo)
    val scope = rememberCoroutineScope()
    val focusRequester = FocusRequester()

    MainScaffold(
        topBar = {
            TopAppBar(
                elevation = topBarElevation,
                backgroundColor = MaterialTheme.colors.primarySurface,
                navigationIcon = {
                    BackNavigationIcon {
                        noteToEdit?.let {
                            viewModel.save()
                        }
                        onBackPressed()
                    }
                },
                actions = {
                    EditNoteTopAppBarActions(
                        archiveIcon = archiveIcon,
                        onArchiveUnarchive = { viewModel.onArchiveUnarchive(noteToEdit) }
                    )
                },
                title = {}
            )
        },
        scaffoldState = scaffoldState
    ) {
        when {
            progress -> {
                FulLScreenProgress()
                uuid?.let {
                    viewModel.getNote(uuid)
                }
            }
        }

        EditNoteFields(
            title = noteToEdit?.title.orEmpty(),
            onTitleChanged = { viewModel.onTitleChanged(noteToEdit, it) },
            content = noteToEdit?.content.orEmpty(),
            onContentChanged = { viewModel.onContentChanged(noteToEdit, it) },
            scope = scope,
            focusRequester = focusRequester
        )
        BackHandler {
            noteToEdit?.let {
                viewModel.save()
            }
            onBackPressed()
        }

        LaunchedEffect(noteArchivedStatus) {
            noteArchivedStatus?.let {
                val message = when (noteArchivedStatus) {
                    is NoteOperationStatus.Archived -> archivedMessage
                    is NoteOperationStatus.Unarchived -> unarchivedMessage
                    else -> ""
                }
                when (snackbarHostState.showSnackbar(message, undoMessage)) {
                    SnackbarResult.ActionPerformed -> {
                        viewModel.onArchiveUnarchive(noteToEdit)
                    }
                    SnackbarResult.Dismissed -> {}
                }
            }
        }
    }
}
