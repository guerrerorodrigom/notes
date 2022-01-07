package com.rodrigoguerrero.notes.creation.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.focus.FocusRequester
import androidx.hilt.navigation.compose.hiltViewModel
import com.rodrigoguerrero.notes.common.ui.components.BackNavigationIcon
import com.rodrigoguerrero.notes.common.ui.components.FulLScreenProgress
import com.rodrigoguerrero.notes.common.ui.components.MainScaffold
import com.rodrigoguerrero.notes.common.ui.topBarElevation
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
    val noteToEdit by viewModel.note.collectAsState(null)
    val progress by viewModel.progress.observeAsState(true)
    val saveCompleted by viewModel.saveCompleted.collectAsState(false)
    val scope = rememberCoroutineScope()
    val focusRequester = FocusRequester()

    MainScaffold(
        topBar = {
            TopAppBar(
                elevation = topBarElevation,
                backgroundColor = MaterialTheme.colors.primarySurface,
                navigationIcon = {
                    BackNavigationIcon {
                        viewModel.save()
                    }
                },
                actions = { EditNoteTopAppBarActions() },
                title = {}
            )
        }
    ) {
        when {
            saveCompleted -> {
                onBackPressed()
            }
            progress -> {
                FulLScreenProgress()
                viewModel.getNote(uuid)
            }
        }

        noteToEdit?.let { note ->
            EditNoteFields(
                title = note.title.orEmpty(),
                onTitleChanged = { viewModel.onTitleChanged(note, it) },
                content = note.content,
                onContentChanged = { viewModel.onContentChanged(note, it) },
                scope = scope,
                focusRequester = focusRequester
            )
            BackHandler {
                viewModel.save()
            }
        }
    }
}
