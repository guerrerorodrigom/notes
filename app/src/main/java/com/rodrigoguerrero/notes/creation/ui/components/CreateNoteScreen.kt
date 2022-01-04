package com.rodrigoguerrero.notes.creation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.common.ui.Screen
import com.rodrigoguerrero.notes.common.ui.components.BackNavigationIcon
import com.rodrigoguerrero.notes.common.ui.fabBottomPadding
import com.rodrigoguerrero.notes.creation.viewmodels.CreateNoteViewModel

class CreateNoteScreen(
    private val onBackPressed: () -> Unit,
    private val onPinNoteClicked: () -> Unit,
    private val onAddNotificationClicked: () -> Unit,
    private val onMoreClicked: () -> Unit,
    private val viewModelStoreOwner: ViewModelStoreOwner
) : Screen {

    @Composable
    override fun TopAppBarActions() {
        EditNoteTopAppBar(
            onPinNoteClicked = onPinNoteClicked,
            onAddNotificationClicked = onAddNotificationClicked,
            onMoreClicked = onMoreClicked
        )
    }

    @Composable
    override fun TopAppBarNavigationIcon() {
        BackNavigationIcon(onBackPressed)
    }

    @Composable
    override fun ScreenTitle() {

    }

    @Composable
    override fun Fab() {
        val viewModel: CreateNoteViewModel = hiltViewModel(viewModelStoreOwner)
        val isFabEnabled by viewModel.isFabEnabled.collectAsState(false)

        if (isFabEnabled) {
            FloatingActionButton(
                onClick = {
                    viewModel.createNote()
                },
                contentColor = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(bottom = fabBottomPadding)
            ) {
                Icon(
                    imageVector = Icons.Filled.Save,
                    contentDescription = stringResource(R.string.save_note)
                )
            }
        }
    }

    @Composable
    override fun BottomAppBar() {

    }
}
