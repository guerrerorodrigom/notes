package com.rodrigoguerrero.notes.creation.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.notes.R

@Composable
fun EditNoteTopAppBar(
    onPinNoteClicked: () -> Unit,
    onAddNotificationClicked: () -> Unit,
    onMoreClicked: () -> Unit,
) {
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

@Preview
@Composable
fun PreviewEditTopAppBar() {
    EditNoteTopAppBar(onPinNoteClicked = { }, onAddNotificationClicked = { }) {

    }
}