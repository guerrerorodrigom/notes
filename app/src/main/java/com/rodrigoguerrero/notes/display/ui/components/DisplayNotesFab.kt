package com.rodrigoguerrero.notes.display.ui.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.rodrigoguerrero.notes.R

@Composable
fun DisplayNotesFab(
    onFabClicked: () -> Unit
) {
    FloatingActionButton(
        onClick = onFabClicked,
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
