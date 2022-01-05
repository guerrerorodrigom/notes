package com.rodrigoguerrero.notes.common.ui.components

import androidx.annotation.StringRes
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun NotesAlertDialog(
    @StringRes confirmText: Int,
    @StringRes dismissText: Int,
    @StringRes title: Int,
    @StringRes content: Int,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = stringResource(title)) },
        text = { Text(text = stringResource(content)) },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = stringResource(confirmText))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(dismissText))
            }
        }
    )
}
