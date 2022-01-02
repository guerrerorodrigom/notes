package com.rodrigoguerrero.notes.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.rodrigoguerrero.notes.R

interface Screen {
    @Composable
    fun TopAppBarActions()

    @Composable
    fun TopAppBarNavigationIcon()

    @Composable
    fun ScreenTitle()
}

class MainScreen(
    private val onNavigationIconClicked: () -> Unit,
    private val onSortNotesClicked: () -> Unit,
    private val onListGridClicked: () -> Unit,
    private val onMoreClicked: () -> Unit,
) : Screen {

    @Composable
    override fun TopAppBarActions() {
        var listModeIcon by remember { mutableStateOf(Icons.Filled.ViewList) }

        IconButton(onClick = { onSortNotesClicked() }) {
            Icon(Icons.Filled.Sort, stringResource(R.string.sort_notes_icon))
        }
        IconButton(onClick = {
            listModeIcon = if (listModeIcon.name == Icons.Filled.ViewList.name) {
                Icons.Filled.GridView
            } else {
                Icons.Filled.ViewList
            }
            onListGridClicked()
        }) {
            Icon(listModeIcon, stringResource(R.string.view_list_mode_icon))
        }
        IconButton(onClick = { onMoreClicked() }) {
            Icon(Icons.Filled.MoreVert, stringResource(R.string.more_icon))
        }
    }

    @Composable
    override fun TopAppBarNavigationIcon() {
        IconButton(onClick = { onNavigationIconClicked() }) {
            Icon(Icons.Filled.Menu, stringResource(R.string.open_drawer_menu_icon))
        }
    }

    @Composable
    override fun ScreenTitle() {
        Text(text = stringResource(R.string.main_top_bar_title))
    }
}

class CreateNoteScreen(
    private val onBackPressed: () -> Unit,
    private val onPinNoteClicked: () -> Unit,
    private val onAddNotificationClicked: () -> Unit,
    private val onMoreClicked: () -> Unit
) : Screen {
    @Composable
    override fun TopAppBarActions() {
        IconButton(onClick = onPinNoteClicked) {
            Icon(Icons.Filled.PushPin, stringResource(R.string.pin_note))
        }
        IconButton(onClick = onAddNotificationClicked) {
            Icon(Icons.Filled.Notifications, stringResource(R.string.add_reminder))
        }
        IconButton(onClick = onMoreClicked) {
            Icon(Icons.Filled.MoreVert, stringResource(R.string.more_icon))
        }
    }

    @Composable
    override fun TopAppBarNavigationIcon() {
        IconButton(onClick = { onBackPressed() }) {
            Icon(Icons.Filled.ArrowBack, stringResource(R.string.back))
        }
    }

    @Composable
    override fun ScreenTitle() {

    }
}
