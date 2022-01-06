package com.rodrigoguerrero.notes.creation.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.common.ui.components.BackNavigationIcon
import com.rodrigoguerrero.notes.common.ui.components.FulLScreenProgress
import com.rodrigoguerrero.notes.common.ui.components.MainScaffold
import com.rodrigoguerrero.notes.common.ui.fabBottomPadding
import com.rodrigoguerrero.notes.common.ui.topBarElevation
import com.rodrigoguerrero.notes.creation.ui.components.EditNoteFields
import com.rodrigoguerrero.notes.creation.ui.components.EditNoteTopAppBarActions
import com.rodrigoguerrero.notes.creation.viewmodels.CreateNoteViewModel

@Composable
fun CreateNoteScreenContent(
    onNavigateBack: () -> Unit
) {
    val viewModel: CreateNoteViewModel = hiltViewModel()
    val isFabEnabled by viewModel.isFabEnabled.collectAsState(false)
    val showProgress by viewModel.processing.collectAsState(false)
    val isNoteSaved by viewModel.successState.collectAsState(false)

    MainScaffold(
        fab = {
            if (isFabEnabled) {
                FloatingActionButton(
                    onClick = {
                        viewModel.createNote()
                    },
                    contentColor = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(bottom = fabBottomPadding)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Save,
                        contentDescription = stringResource(R.string.save_note)
                    )
                }
            }
        },
        topBar = {
            TopAppBar(
                elevation = topBarElevation,
                backgroundColor = MaterialTheme.colors.primarySurface,
                navigationIcon = { BackNavigationIcon(onNavigateBack) },
                actions = { EditNoteTopAppBarActions() },
                title = {}
            )
        }
    ) {
        when {
            isNoteSaved -> onNavigateBack()
            showProgress -> FulLScreenProgress()
            else -> CreateNote(viewModel)
        }
    }
}

@Composable
fun CreateNote(viewModel: CreateNoteViewModel) {
    val title by viewModel.title.collectAsState()
    val content by viewModel.content.collectAsState()
    val scope = rememberCoroutineScope()

    EditNoteFields(
        title = title,
        onTitleChanged = { viewModel.title.value = it },
        content = content,
        onContentChanged = { viewModel.content.value = it },
        scope = scope,
        readOnlyFields = false
    )
}
