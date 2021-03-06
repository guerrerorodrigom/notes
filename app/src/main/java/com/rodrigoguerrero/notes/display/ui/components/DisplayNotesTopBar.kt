package com.rodrigoguerrero.notes.display.ui.components

import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.notes.R

@Composable
fun DisplayNotesTopBar(
    listModeIcon: ImageVector,
    @StringRes title: Int,
    onToggleDisplayMode: () -> Unit,
    onNavigationIconClicked: () -> Unit
) {
    TopAppBar(
        title = { Text(text = stringResource(title)) },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
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
        Icon(Icons.Filled.MoreVert, stringResource(R.string.archive_unarchive))
    }
}
