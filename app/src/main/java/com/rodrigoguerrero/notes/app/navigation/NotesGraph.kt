package com.rodrigoguerrero.notes.app.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rodrigoguerrero.notes.app.navigation.MainDestinations.ALL_NOTES
import com.rodrigoguerrero.notes.app.navigation.MainDestinations.ARCHIVE
import com.rodrigoguerrero.notes.app.navigation.MainDestinations.DEFAULT
import com.rodrigoguerrero.notes.app.navigation.MainDestinations.DELETED
import com.rodrigoguerrero.notes.app.navigation.MainDestinations.NOTEBOOKS
import com.rodrigoguerrero.notes.app.navigation.MainDestinations.SETTINGS
import com.rodrigoguerrero.notes.app.navigation.MainDestinations.TAGS
import com.rodrigoguerrero.notes.configuration.ui.screens.SettingsScreen
import com.rodrigoguerrero.notes.creation.navigation.NoteCreationDestinations.CREATE_TEXT_NOTE
import com.rodrigoguerrero.notes.creation.ui.screens.CreateNoteScreenContent
import com.rodrigoguerrero.notes.creation.viewmodels.CreateNoteViewModel
import com.rodrigoguerrero.notes.display.ui.screens.ArchiveScreen
import com.rodrigoguerrero.notes.display.ui.screens.DeletedScreen
import com.rodrigoguerrero.notes.display.ui.screens.NotesListScreen
import com.rodrigoguerrero.notes.display.viewmodels.NoteListViewModel
import com.rodrigoguerrero.notes.tags.ui.screens.TagsScreen
import com.rodrigoguerrero.notes.ui.screens.*

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun NotesGraph(
    navController: NavHostController,
    createNoteViewModel: CreateNoteViewModel,
    notesListViewModel: NoteListViewModel,
    startDestination: String = DEFAULT
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(ALL_NOTES) {
            NotesListScreen(notesListViewModel)
        }
        composable(ARCHIVE) {
            ArchiveScreen()
        }
        composable(DELETED) {
            DeletedScreen()
        }
        composable(NOTEBOOKS) {
            NotebooksScreen()
        }
        composable(TAGS) {
            TagsScreen()
        }
        composable(SETTINGS) {
            SettingsScreen()
        }
        composable(CREATE_TEXT_NOTE) {
            CreateNoteScreenContent(createNoteViewModel) {
                navController.popBackStack()
            }
        }
    }
}
