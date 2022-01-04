package com.rodrigoguerrero.notes.display.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.common.ui.components.FulLScreenProgress
import com.rodrigoguerrero.notes.common.ui.components.FullScreenLottie
import com.rodrigoguerrero.notes.common.ui.padding8
import com.rodrigoguerrero.notes.display.ui.components.TextNoteCard
import com.rodrigoguerrero.notes.display.viewmodels.NoteListViewModel

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun NotesListScreen(
    viewModel: NoteListViewModel
) {
    val notes: List<Note> by viewModel.notes.collectAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.observeAsState(true)
    val isEmpty by viewModel.isEmpty.observeAsState(false)
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.create_note))
    val lottieProgress by animateLottieCompositionAsState(
        composition = lottieComposition,
        iterations = LottieConstants.IterateForever
    )
    val isListMode by viewModel.isListMode.observeAsState(true)

    when {
        isLoading ->
            FulLScreenProgress()
        isEmpty -> FullScreenLottie(composition = lottieComposition, progress = lottieProgress)
        isListMode -> NotesList(notes)
        !isListMode -> NotesGrid(notes)
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun NotesGrid(notes: List<Note>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier.padding(top = padding8)
    ) {
        items(notes) { note ->
            TextNoteCard(title = note.title.orEmpty(), content = note.content)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun NotesList(notes: List<Note>) {
    LazyColumn(
        modifier = Modifier.padding(top = padding8)
    ) {
        items(notes) {
            TextNoteCard(title = it.title.orEmpty(), content = it.content)
        }
    }
}
