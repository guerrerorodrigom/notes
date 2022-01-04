package com.rodrigoguerrero.notes.common.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.rodrigoguerrero.notes.R

@Composable
fun BackNavigationIcon(
    onBackPressed: () -> Unit
) {
    IconButton(onClick = { onBackPressed() }) {
        Icon(Icons.Filled.ArrowBack, stringResource(R.string.back))
    }
}
