package com.rodrigoguerrero.notes.display.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.common.ui.cardElevation
import com.rodrigoguerrero.notes.common.ui.padding16
import com.rodrigoguerrero.notes.common.ui.padding4
import com.rodrigoguerrero.notes.common.ui.padding8
import java.util.*

@ExperimentalMaterialApi
@Composable
fun TextNoteCard(
    note: Note,
    onNoteClicked: (UUID) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(horizontal = padding8, vertical = padding4)
            .fillMaxWidth(),
        elevation = cardElevation,
        onClick = { onNoteClicked(note.id) }
    ) {
        Column(
            modifier = Modifier.padding(padding16)
        ) {
            if (note.title?.isNotEmpty() == true) {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(bottom = padding8)
                )
            }
            Text(
                text = note.content,
                style = MaterialTheme.typography.body1,
                maxLines = 8,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewNoteCard() {
    val note = Note(
        id = UUID.randomUUID(),
        title = "Note Title",
        content = "This is the note content and it can be very very very very very very very very very very long.",
        createdDate = Date().time,
        modifiedDate = Date().time,
        isDeleted = false,
        isArchived = false
    )
    TextNoteCard(note, {})
}