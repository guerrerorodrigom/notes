package com.rodrigoguerrero.notes.display.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.common.ui.topBarElevation

@Composable
fun DisplayNotesTopBar(
    listModeIcon: ImageVector,
    onToggleDisplayMode: () -> Unit,
    onNavigationIconClicked: () -> Unit
) {
    TopAppBar(
        elevation = topBarElevation,
        title = { Text(text = stringResource(R.string.main_top_bar_title)) },
        backgroundColor = MaterialTheme.colors.primarySurface,
        navigationIcon = {
            IconButton(onClick = onNavigationIconClicked) {
                Icon(Icons.Filled.Menu, stringResource(R.string.open_drawer_menu_icon))
            }
        },
        actions = {
            DisplayNotesTopBarActions(
                onToggleDisplayMode = onToggleDisplayMode,
                listModeIcon = listModeIcon
            )
        }
    )
}

@Composable
fun DisplayNotesTopBarActions(
    onToggleDisplayMode: () -> Unit,
    listModeIcon: ImageVector
) {
    IconButton(onClick = { }) {
        Icon(Icons.Filled.Sort, stringResource(R.string.sort_notes_icon))
    }
    IconButton(onClick = onToggleDisplayMode) {
        Icon(listModeIcon, stringResource(R.string.view_list_mode_icon))
    }
    IconButton(onClick = { }) {
        Icon(Icons.Filled.MoreVert, stringResource(R.string.more_icon))
    }
}
