package com.rodrigoguerrero.notes.display.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.common.ui.padding8
import java.util.*

@ExperimentalMaterialApi
@Composable
fun NotesList(
    notes: List<Note>,
    pinnedNotes: List<Note>,
    onNoteClicked: (UUID) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = padding8)
            .verticalScroll(rememberScrollState())
    ) {

        if (pinnedNotes.isNotEmpty()) {
            item {
                Text(
                    text = stringResource(R.string.pinned_notes),
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                )
            }
        }
        items(pinnedNotes) { note ->
            TextNoteCard(note, onNoteClicked)
        }

        item {
            Text(
                text = stringResource(R.string.other_notes),
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            )
        }
        items(notes) { note ->
            TextNoteCard(note, onNoteClicked)
        }
    }
}
