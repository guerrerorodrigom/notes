package com.rodrigoguerrero.notes.creation.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.primarySurface
import androidx.compose.runtime.*
import androidx.compose.ui.focus.FocusRequester
import androidx.hilt.navigation.compose.hiltViewModel
import com.rodrigoguerrero.notes.common.ui.components.BackNavigationIcon
import com.rodrigoguerrero.notes.common.ui.components.FulLScreenProgress
import com.rodrigoguerrero.notes.common.ui.components.MainScaffold
import com.rodrigoguerrero.notes.common.ui.topBarElevation
import com.rodrigoguerrero.notes.creation.ui.components.EditNoteFields
import com.rodrigoguerrero.notes.creation.ui.components.EditNoteTopAppBarActions
import com.rodrigoguerrero.notes.creation.viewmodels.CreateNoteViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun CreateNoteScreenContent(
    onNavigateBack: () -> Unit
) {
    val viewModel: CreateNoteViewModel = hiltViewModel()
    val showProgress by viewModel.processing.collectAsState(false)
    val isNoteSaved by viewModel.successState.collectAsState(false)
    val title by viewModel.title.collectAsState()
    val content by viewModel.content.collectAsState()
    val scope = rememberCoroutineScope()

    MainScaffold(
        topBar = {
            TopAppBar(
                elevation = topBarElevation,
                backgroundColor = MaterialTheme.colors.primarySurface,
                navigationIcon = {
                    BackNavigationIcon {
                        viewModel.createNote()
                        onNavigateBack()
                    }
                },
                actions = {
                    EditNoteTopAppBarActions(
                        archiveIcon = Icons.Filled.Archive,
                        onArchiveUnarchive = { }
                    )
                },
                title = {}
            )
        }
    ) {
        when {
            isNoteSaved -> onNavigateBack()
            showProgress -> FulLScreenProgress()
            else -> CreateNote(
                title = title,
                content = content,
                scope = scope,
                onTitleChanged = { viewModel.onTitleChanged(it) },
                onContentChanged = { viewModel.onContentChanged(it) },
                onBackPressed = {
                    viewModel.createNote()
                    onNavigateBack()
                }
            )
        }
    }
}

@Composable
fun CreateNote(
    title: String,
    content: String,
    scope: CoroutineScope,
    onTitleChanged: (String) -> Unit,
    onContentChanged: (String) -> Unit,
    onBackPressed: () -> Unit
) {

    val focusRequester = FocusRequester()

    EditNoteFields(
        title = title,
        onTitleChanged = { onTitleChanged(it) },
        content = content,
        onContentChanged = { onContentChanged(it) },
        scope = scope,
        focusRequester = focusRequester
    )

    BackHandler {
        onBackPressed()
    }

    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { }
    }
}
