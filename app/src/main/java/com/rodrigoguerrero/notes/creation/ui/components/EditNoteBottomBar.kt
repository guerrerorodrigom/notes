package com.rodrigoguerrero.notes.creation.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.More
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.common.ui.bottomBarElevation
import com.rodrigoguerrero.notes.display.ui.components.BottomAppBarIcon

@Composable
fun EditNoteBottomBar(
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = bottomBarElevation
    ) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            BottomAppBarIcon(
                onClick = { },
                icon = Icons.Outlined.AddBox,
                description = stringResource(R.string.more_options)
            )
            BottomAppBarIcon(
                onClick = { },
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
    EditNoteBottomBar()
}