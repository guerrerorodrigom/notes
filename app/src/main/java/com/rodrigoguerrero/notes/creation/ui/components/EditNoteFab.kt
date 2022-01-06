package com.rodrigoguerrero.notes.creation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.common.ui.fabBottomPadding

@Composable
fun EditNoteFab(
    areFieldsEnabled: Boolean,
    onSaveClicked: () -> Unit,
    onEditClicked: () -> Unit
) {
    if (areFieldsEnabled) {
        FloatingActionButton(
            onClick = onSaveClicked,
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
            onClick = onEditClicked,
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
