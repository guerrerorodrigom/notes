package com.rodrigoguerrero.notes.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.ui.dimens.topBarElevation
import com.rodrigoguerrero.notes.ui.theme.NotesTheme

@Composable
fun MainTopBar(
    onNavigationIconClicked: () -> Unit,
    onSortNotesClicked: () -> Unit,
    onListGridClicked: () -> Unit,
    onMoreClicked: () -> Unit,
) {
    var listModeIcon by remember { mutableStateOf(Icons.Filled.ViewList) }
    TopAppBar(
        elevation = topBarElevation,
        title = { Text(text = stringResource(R.string.main_top_bar_title)) },
        backgroundColor = MaterialTheme.colors.primarySurface,
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClicked() }) {
                Icon(Icons.Filled.Menu, stringResource(R.string.open_drawer_menu_icon))
            }
        },
        actions = {
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
    )
}

@Preview
@Composable
fun PreviewMainTopBar() {
    NotesTheme {
        MainTopBar(
            onListGridClicked = {},
            onMoreClicked = {},
            onNavigationIconClicked = {},
            onSortNotesClicked = {},
        )
    }
}
