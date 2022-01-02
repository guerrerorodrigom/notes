package com.rodrigoguerrero.notes.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rodrigoguerrero.notes.ui.MainDestinations.ALL_NOTES
import com.rodrigoguerrero.notes.ui.MainDestinations.ARCHIVE
import com.rodrigoguerrero.notes.ui.MainDestinations.DEFAULT
import com.rodrigoguerrero.notes.ui.MainDestinations.DELETED
import com.rodrigoguerrero.notes.ui.MainDestinations.NOTEBOOKS
import com.rodrigoguerrero.notes.ui.MainDestinations.SETTINGS
import com.rodrigoguerrero.notes.ui.MainDestinations.TAGS
import com.rodrigoguerrero.notes.ui.SecondaryDestinations.CREATE_TEXT_NOTE

@Composable
fun NotesGraph(
    navController: NavHostController,
    startDestination: String = DEFAULT
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(ALL_NOTES) {
            NotesListScreen()
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
            CreateNoteScreenContent()
        }
    }
}
