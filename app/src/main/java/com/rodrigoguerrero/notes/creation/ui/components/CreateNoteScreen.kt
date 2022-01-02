package com.rodrigoguerrero.notes.creation.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.common.ui.Screen

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
