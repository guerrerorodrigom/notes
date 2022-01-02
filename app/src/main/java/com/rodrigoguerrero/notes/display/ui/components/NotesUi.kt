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
import com.rodrigoguerrero.notes.common.ui.cardElevation
import com.rodrigoguerrero.notes.common.ui.padding16
import com.rodrigoguerrero.notes.common.ui.padding8

@ExperimentalMaterialApi
@Composable
fun TextNoteCard(
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(padding16)
            .fillMaxWidth(),
        elevation = cardElevation,
        onClick = {}
    ) {
        Column(
            modifier = Modifier.padding(padding16)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = padding8)
            )
            Text(
                text = content,
                style = MaterialTheme.typography.body1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewNoteCard() {
    TextNoteCard(
        "Note Title",
        "This is the note content and it can be very very very very very very very very very very long."
    )
}