package com.rodrigoguerrero.notes.creation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.rodrigoguerrero.notes.common.ui.components.FulLScreenProgress
import com.rodrigoguerrero.notes.creation.ui.components.EditNoteFields
import com.rodrigoguerrero.notes.creation.viewmodels.CreateNoteViewModel

@Composable
fun CreateNoteScreenContent(
    viewModelStoreOwner: ViewModelStoreOwner,
    viewModel: CreateNoteViewModel = hiltViewModel(viewModelStoreOwner),
    onNavigateBack: () -> Unit
) {
    val showProgress by viewModel.processing.collectAsState(false)
    val isNoteSaved by viewModel.successState.collectAsState(false)

    when {
        isNoteSaved -> onNavigateBack()
        showProgress -> FulLScreenProgress()
        else -> CreateNote(viewModel)
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

@Preview
@Composable
fun PreviewCreateNoteScreen() {
    CreateNoteScreenContent(hiltViewModel()) {}
}