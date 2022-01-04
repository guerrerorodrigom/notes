package com.rodrigoguerrero.notes.display.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.common.ui.Screen
import com.rodrigoguerrero.notes.display.viewmodels.NoteListViewModel

class NotesListScreen(
    private val onNavigationIconClicked: () -> Unit,
    private val onSortNotesClicked: () -> Unit,
    private val onMoreClicked: () -> Unit,
    private val onFabClicked: () -> Unit,
    private val onBottomAppIconClicked: (String) -> Unit,
    private val viewModel: NoteListViewModel
) : Screen {

    @Composable
    override fun TopAppBarActions() {
        val isListMode by viewModel.isListMode.collectAsState(true)
        val listModeIcon by viewModel.listModeIcon.observeAsState(Icons.Filled.GridView)

        IconButton(onClick = { onSortNotesClicked() }) {
            Icon(Icons.Filled.Sort, stringResource(R.string.sort_notes_icon))
        }
        IconButton(onClick = { viewModel.setDisplayMode(!isListMode) }) {
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

    @Composable
    override fun Fab() {
        FloatingActionButton(
            onClick = { onFabClicked() },
            contentColor = MaterialTheme.colors.onSurface
        ) {
            Icon(
                imageVector = Icons.Filled.AddCircleOutline,
                contentDescription = stringResource(
                    R.string.create_new_note
                )
            )
        }
    }

    @Composable
    override fun BottomAppBar() {
        NotesListBottomAppBar(onBottomAppIconClicked = onBottomAppIconClicked)
    }
}
