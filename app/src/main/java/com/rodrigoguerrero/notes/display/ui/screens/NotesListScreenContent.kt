package com.rodrigoguerrero.notes.display.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.insets.statusBarsPadding
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.common.ui.components.FulLScreenProgress
import com.rodrigoguerrero.notes.common.ui.components.FullScreenLottie
import com.rodrigoguerrero.notes.common.ui.components.StaggeredVerticalGrid
import com.rodrigoguerrero.notes.common.ui.maxColumnWithStaggeredGrid
import com.rodrigoguerrero.notes.common.ui.padding4
import com.rodrigoguerrero.notes.common.ui.padding8
import com.rodrigoguerrero.notes.display.ui.components.TextNoteCard
import com.rodrigoguerrero.notes.display.viewmodels.NoteListViewModel
import java.util.*

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun NotesListScreen(
    viewModelStoreOwner: ViewModelStoreOwner,
    onNoteClicked: (UUID) -> Unit,
    viewModel: NoteListViewModel = hiltViewModel(viewModelStoreOwner)
) {
    val notes: List<Note> by viewModel.notes.collectAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.collectAsState(true)
    val isEmpty by viewModel.isEmpty.observeAsState(false)
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.create_note))
    val lottieProgress by animateLottieCompositionAsState(
        composition = lottieComposition,
        iterations = LottieConstants.IterateForever
    )
    val isListMode by viewModel.isListMode.collectAsState(true)

    when {
        isLoading -> FulLScreenProgress()
        isEmpty -> FullScreenLottie(composition = lottieComposition, progress = lottieProgress)
        isListMode -> NotesList(notes, onNoteClicked)
        !isListMode -> NotesGrid(notes, onNoteClicked)
    }
}

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
