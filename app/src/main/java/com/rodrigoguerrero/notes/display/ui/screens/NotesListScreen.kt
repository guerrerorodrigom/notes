package com.rodrigoguerrero.notes.display.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.common.ui.padding16
import com.rodrigoguerrero.notes.common.ui.padding8
import com.rodrigoguerrero.notes.display.ui.components.TextNoteCard
import com.rodrigoguerrero.notes.display.viewmodels.NoteListViewModel

@ExperimentalMaterialApi
@Composable
fun NotesListScreen(
    viewModel: NoteListViewModel = hiltViewModel()
) {
    val notes: List<Note> by viewModel.notes.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier.padding(top = padding8)
    ) {
        items(notes) {
            TextNoteCard(title = it.title.orEmpty(), content = it.content)
        }
    }
}
