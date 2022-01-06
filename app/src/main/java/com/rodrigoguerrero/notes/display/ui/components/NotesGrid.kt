package com.rodrigoguerrero.notes.display.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.statusBarsPadding
import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.common.ui.components.StaggeredVerticalGrid
import com.rodrigoguerrero.notes.common.ui.maxColumnWithStaggeredGrid
import com.rodrigoguerrero.notes.common.ui.padding4
import java.util.*

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun NotesGrid(
    notes: List<Note>,
    onNoteClicked: (UUID) -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
    ) {
        StaggeredVerticalGrid(
            maxColumnWidth = maxColumnWithStaggeredGrid,
            modifier = Modifier
                .padding(padding4)
        ) {
            notes.forEach { note ->
                TextNoteCard(note, onNoteClicked)
            }
        }
    }
}
