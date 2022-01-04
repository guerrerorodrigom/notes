package com.rodrigoguerrero.notes.app.ui.screens

import androidx.compose.material.DrawerState
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import com.rodrigoguerrero.notes.common.ui.Screen
import com.rodrigoguerrero.notes.creation.navigation.NoteCreationDestinations.CREATE_TEXT_NOTE
import com.rodrigoguerrero.notes.creation.ui.components.CreateNoteScreen
import com.rodrigoguerrero.notes.display.ui.components.NotesListScreen
import com.rodrigoguerrero.notes.creation.viewmodels.CreateNoteViewModel
import com.rodrigoguerrero.notes.display.viewmodels.NoteListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun getScreen(
    currentDestination: String,
    navController: NavHostController,
    scope: CoroutineScope,
    drawerState: DrawerState,
    viewModelStoreOwner: ViewModelStoreOwner
): Screen {
    return when (currentDestination) {
        CREATE_TEXT_NOTE -> CreateNoteScreen(
            onBackPressed = { navController.popBackStack() },
            onAddNotificationClicked = {},
            onPinNoteClicked = {},
            onMoreClicked = {},
            viewModelStoreOwner = viewModelStoreOwner
        )
        else -> NotesListScreen(
            onNavigationIconClicked = {
                scope.launch {
                    drawerState.open()
                }
            },
            onSortNotesClicked = { /*TODO*/ },
            onMoreClicked = {},
            onFabClicked = { navController.navigate(CREATE_TEXT_NOTE) },
            onBottomAppIconClicked = { },
            viewModelStoreOwner = viewModelStoreOwner
        )
    }
}
