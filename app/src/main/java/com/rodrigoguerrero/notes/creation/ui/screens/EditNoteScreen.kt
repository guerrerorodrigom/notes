package com.rodrigoguerrero.notes.creation.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.common.ui.components.BackNavigationIcon
import com.rodrigoguerrero.notes.common.ui.components.FulLScreenProgress
import com.rodrigoguerrero.notes.common.ui.components.MainScaffold
import com.rodrigoguerrero.notes.creation.models.ReminderSuggestion
import com.rodrigoguerrero.notes.creation.ui.components.*
import com.rodrigoguerrero.notes.creation.viewmodels.EditNoteViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import java.util.*

enum class BottomSheetType {
    COLOR, REMINDER
}

@ExperimentalMaterialApi
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
    val archiveIcon by viewModel.archiveIcon.collectAsState(Icons.Filled.Archive)
    val pinIcon by viewModel.pinIcon.collectAsState(Icons.Outlined.PushPin)
    val noteArchivedStatus by viewModel.archiveNote.collectAsState(null)
    val snackbarHostState = remember { SnackbarHostState() }
    val scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState)
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val noteColor by viewModel.noteColor.collectAsState(Color.Transparent)
    val bottomSheetType by viewModel.bottomSheetType.collectAsState(null)
    val archivedMessage = stringResource(R.string.note_archived)
    val unarchivedMessage = stringResource(R.string.note_unarchived)
    val undoMessage = stringResource(R.string.undo)
    val scope = rememberCoroutineScope()
    val focusRequester = FocusRequester()
    val systemUiController = rememberSystemUiController()

    ModalBottomSheetLayout(
        sheetContent = {
            Box(Modifier.defaultMinSize(minHeight = 1.dp)) {
                when (bottomSheetType) {
                    BottomSheetType.REMINDER -> {
                        val laterToday = ReminderSuggestion(0L, "Later Today", "18:00")
                        val tomorrowMorning = ReminderSuggestion(0L, "Tomorrow morning", "08:00")
                        val sundayMorning = ReminderSuggestion(0L, "Sunday morning", "Sun 08:00")
                        val suggestions = listOf(laterToday, tomorrowMorning, sundayMorning)

                        ReminderSelectorBottomSheet(
                            reminderSuggestions = suggestions,
                            selectedColor = noteColor,
                            onShowDatePicker = {},
                            onSuggestionSelected = {}
                        )
                    }
                    BottomSheetType.COLOR -> {
                        ColorSelectorBottomSheet(
                            selectedColor = noteColor,
                            onColorSelected = { color ->
                                viewModel.onNoteColorSelected(noteToEdit, color)
                            }
                        )
                    }
                }
            }
        },
        sheetState = bottomSheetState,
    ) {
        MainScaffold(
            topBar = {
                TopAppBar(
                    elevation = 0.dp,
                    backgroundColor = noteColor,
                    navigationIcon = {
                        BackNavigationIcon {
                            noteToEdit?.let {
                                viewModel.save()
                            }
                            systemUiController.setStatusBarColor(Color.Transparent)
                            onBackPressed()
                        }
                    },
                    actions = {
                        EditNoteTopAppBarActions(
                            archiveIcon = archiveIcon,
                            onArchiveUnarchive = { viewModel.onArchiveUnarchive(noteToEdit) },
                            pinIcon = pinIcon,
                            onPinUnpin = { viewModel.onPinUnpin(noteToEdit) },
                            onAddReminder = {
                                viewModel.setBottomSheetType(BottomSheetType.REMINDER)
                                scope.launch { bottomSheetState.show() }
                            }
                        )
                    },
                    title = {}
                )
            },
            bottomBar = {
                EditNoteBottomBar(
                    onShowColorSelector = {
                        scope.launch {
                            viewModel.setBottomSheetType(BottomSheetType.COLOR)
                            bottomSheetState.show()
                        }
                    },
                    backgroundColor = noteColor
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
                focusRequester = focusRequester,
                backgroundColor = noteColor
            )
            BackHandler {
                noteToEdit?.let {
                    viewModel.save()
                }
                systemUiController.setStatusBarColor(Color.Transparent)
                onBackPressed()
            }

            systemUiController.setStatusBarColor(noteColor, true)

            LaunchedEffect(noteArchivedStatus) {
                noteArchivedStatus?.let {
                    val message = if (noteArchivedStatus == true) {
                        archivedMessage
                    } else {
                        unarchivedMessage
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
}
