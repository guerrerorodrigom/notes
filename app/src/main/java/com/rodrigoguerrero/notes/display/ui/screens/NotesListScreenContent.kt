package com.rodrigoguerrero.notes.display.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.insets.ProvideWindowInsets
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.app.navigation.MainDestinations.ALL_NOTES
import com.rodrigoguerrero.notes.app.ui.components.MainDrawerMenu
import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.common.ui.components.FulLScreenProgress
import com.rodrigoguerrero.notes.common.ui.components.FullScreenLottie
import com.rodrigoguerrero.notes.common.ui.components.MainScaffold
import com.rodrigoguerrero.notes.display.ui.components.*
import com.rodrigoguerrero.notes.display.viewmodels.NoteListViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import java.util.*

@FlowPreview
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun NotesListScreen(
    onNoteClicked: (UUID) -> Unit,
    onCreateNote: () -> Unit,
    navigateTo: (String) -> Unit
) {
    val viewModel: NoteListViewModel = hiltViewModel()
    val notes: List<Note> by viewModel.notes.collectAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.collectAsState(true)
    val isEmpty by viewModel.isEmpty.observeAsState(false)
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.create_note))
    val lottieProgress by animateLottieCompositionAsState(
        composition = lottieComposition,
        iterations = LottieConstants.IterateForever
    )
    val isListMode by viewModel.isListMode.collectAsState(true)
    val listModeIcon by viewModel.listModeIcon.observeAsState(Icons.Filled.GridView)
    val title by viewModel.title.collectAsState(R.string.main_top_bar_title)
    val selectedMenu by viewModel.selectedMenu.collectAsState(ALL_NOTES)
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    ProvideWindowInsets {
        MainScaffold(
            fab = { DisplayNotesFab { onCreateNote() } },
            topBar = {
                DisplayNotesTopBar(
                    listModeIcon = listModeIcon,
                    onNavigationIconClicked = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    },
                    onToggleDisplayMode = { viewModel.toggleListMode() },
                    title = title
                )
            },
            bottomBar = { NotesListBottomAppBar(onBottomAppIconClicked = {}) },
            drawer = {
                MainDrawerMenu(
                    onNavigateFromMenu = { destination ->
                        if (!viewModel.handleNoteDisplayType(destination)) {
                            navigateTo(destination)
                        }
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    },
                    currentRoute = selectedMenu,
                )
            }, scaffoldState = scaffoldState
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                when {
                    isLoading -> FulLScreenProgress()
                    isEmpty -> FullScreenLottie(
                        composition = lottieComposition,
                        progress = lottieProgress
                    )
                    isListMode -> NotesList(notes, onNoteClicked)
                    !isListMode -> NotesGrid(notes, onNoteClicked)
                }
            }
        }
    }
}
