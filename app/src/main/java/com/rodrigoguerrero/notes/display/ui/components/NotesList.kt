package com.rodrigoguerrero.notes.display.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.common.ui.padding8
import java.util.*

@ExperimentalMaterialApi
@Composable
fun NotesList(
    notes: List<Note>,
    onNoteClicked: (UUID) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = padding8)
    ) {
        items(notes) { note ->
            TextNoteCard(note, onNoteClicked)
        }
    }
}
