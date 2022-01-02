package com.rodrigoguerrero.notes.app.ui.screens

import androidx.compose.material.DrawerState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rodrigoguerrero.notes.common.ui.Screen
import com.rodrigoguerrero.notes.creation.navigation.NoteCreationDestinations
import com.rodrigoguerrero.notes.creation.navigation.NoteCreationDestinations.CREATE_TEXT_NOTE
import com.rodrigoguerrero.notes.creation.ui.components.CreateNoteScreen
import com.rodrigoguerrero.notes.creation.viewmodels.CreateNoteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun getScreen(
    currentDestination: String,
    navController: NavHostController,
    scope: CoroutineScope,
    drawerState: DrawerState,
    createNoteViewModel: CreateNoteViewModel
): Screen {
    return when (currentDestination) {
        CREATE_TEXT_NOTE -> CreateNoteScreen(
            onBackPressed = { navController.popBackStack() },
            onAddNotificationClicked = {},
            onPinNoteClicked = {},
            onMoreClicked = {},
            viewModel = createNoteViewModel
        )
        else -> MainScreen(
            onNavigationIconClicked = {
                scope.launch {
                    drawerState.open()
                }
            },
            onSortNotesClicked = { /*TODO*/ },
            onListGridClicked = { /*TODO*/ },
            onMoreClicked = {},
            onFabClicked = { navController.navigate(CREATE_TEXT_NOTE) },
            onBottomAppIconClicked = { }
        )
    }
}
