package com.rodrigoguerrero.notes.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.ui.dimens.topBarElevation

@Composable
fun CreateNoteTopBarUi(
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        elevation = topBarElevation,
        backgroundColor = MaterialTheme.colors.primarySurface,
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(Icons.Filled.ArrowBack, stringResource(R.string.back))
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(Icons.Filled.PushPin, stringResource(R.string.pin_note))
            }
            IconButton(onClick = { }) {
                Icon(Icons.Filled.Notifications, stringResource(R.string.add_reminder))
            }
            IconButton(onClick = { }) {
                Icon(Icons.Filled.MoreVert, stringResource(R.string.more_icon))
            }
        },
        title = { }
    )
}