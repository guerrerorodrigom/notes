package com.rodrigoguerrero.notes.creation.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.rodrigoguerrero.notes.R

@Composable
fun EditNoteTopAppBarActions(
    archiveIcon: ImageVector,
    onArchiveUnarchive: () -> Unit
) {
    IconButton(onClick = {}) {
        Icon(Icons.Filled.PushPin, stringResource(R.string.pin_note))
    }
    IconButton(onClick = {}) {
        Icon(Icons.Filled.Notifications, stringResource(R.string.add_reminder))
    }
    IconButton(onClick = onArchiveUnarchive) {
        Icon(archiveIcon, stringResource(R.string.archive_unarchive))
    }
}
