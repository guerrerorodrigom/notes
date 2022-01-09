package com.rodrigoguerrero.notes.creation.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.BottomAppBar
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.display.ui.components.BottomAppBarIcon

@Composable
fun EditNoteBottomBar(
    onShowColorSelector: () -> Unit,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        modifier = modifier,
        backgroundColor = backgroundColor,
        elevation = 0.dp
    ) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            BottomAppBarIcon(
                onClick = { },
                icon = Icons.Outlined.AddBox,
                description = stringResource(R.string.more_options)
            )
            BottomAppBarIcon(
                onClick = onShowColorSelector,
                icon = Icons.Outlined.Palette,
                description = stringResource(R.string.note_colors)
            )
            Spacer(
                modifier = Modifier.weight(1.0f)
            )
            BottomAppBarIcon(
                onClick = { },
                icon = Icons.Outlined.MoreVert,
                description = stringResource(R.string.other_options)
            )
        }
    }
}

@Preview
@Composable
fun PreviewEditNoteBottomBar() {
    EditNoteBottomBar(onShowColorSelector = {}, Color.Transparent)
}