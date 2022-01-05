package com.rodrigoguerrero.notes.creation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
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
import com.rodrigoguerrero.notes.creation.viewmodels.EditNoteViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class EditNoteScreen(
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
        val viewModel: EditNoteViewModel = hiltViewModel(viewModelStoreOwner)
        val hasChanged by viewModel.hasChanged.collectAsState(false)
        BackNavigationIcon {
            if (!hasChanged) {
                onBackPressed()
            } else {
                viewModel.showWarning()
            }
        }
    }

    @Composable
    override fun ScreenTitle() {

    }

    @Composable
    override fun Fab() {
        val viewModel: EditNoteViewModel = hiltViewModel(viewModelStoreOwner)
        val areFieldsEnabled by viewModel.areFieldsEnabled.collectAsState(false)

        if (areFieldsEnabled) {
            FloatingActionButton(
                onClick = { viewModel.save() },
                contentColor = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(bottom = fabBottomPadding)
            ) {
                Icon(
                    imageVector = Icons.Filled.Save,
                    contentDescription = stringResource(R.string.edit_note)
                )
            }
        } else {
            FloatingActionButton(
                onClick = { viewModel.enableFields() },
                contentColor = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(bottom = fabBottomPadding)
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = stringResource(R.string.edit_note)
                )
            }
        }
    }

    @Composable
    override fun BottomAppBar() {

    }
}
