package com.rodrigoguerrero.notes.app.ui.screens

import androidx.compose.material.DrawerState
import androidx.navigation.NavHostController
import com.rodrigoguerrero.notes.common.ui.Screen
import com.rodrigoguerrero.notes.creation.navigation.NoteCreationDestinations.CREATE_TEXT_NOTE
import com.rodrigoguerrero.notes.creation.ui.components.CreateNoteScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun getScreen(
    currentDestination: String,
    navController: NavHostController,
    scope: CoroutineScope,
    drawerState: DrawerState
): Screen {
    return when (currentDestination) {
        CREATE_TEXT_NOTE -> CreateNoteScreen(
            onBackPressed = { navController.popBackStack() },
            onAddNotificationClicked = {},
            onPinNoteClicked = {},
            onMoreClicked = {}
        )
        else -> MainScreen(
            onNavigationIconClicked = {
                scope.launch {
                    drawerState.open()
                }
            },
            onSortNotesClicked = { /*TODO*/ },
            onListGridClicked = { /*TODO*/ },
            onMoreClicked = {}
        )
    }
}
