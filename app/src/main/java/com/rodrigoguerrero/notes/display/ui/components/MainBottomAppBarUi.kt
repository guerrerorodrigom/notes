package com.rodrigoguerrero.notes.display.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.notes.app.ui.models.bottomBarItems
import com.rodrigoguerrero.notes.common.ui.bottomBarElevation
import com.rodrigoguerrero.notes.app.theme.NotesTheme

@Composable
fun NotesListBottomAppBar(
    onBottomAppIconClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        modifier = modifier,
        cutoutShape = CircleShape,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = bottomBarElevation
    ) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            bottomBarItems.forEach { item ->
                BottomAppBarIcon(
                    onClick = { onBottomAppIconClicked(item.route) },
                    icon = item.icon,
                    description = ""
                )
            }
        }
    }
}

@Composable
fun BottomAppBarIcon(
    onClick: () -> Unit,
    icon: ImageVector,
    description: String
) {
    IconButton(onClick = onClick) {
        Icon(imageVector = icon, contentDescription = description)
    }
}

@Preview
@Composable
fun PreviewMainBottomAppBar() {
    NotesTheme {
        NotesListBottomAppBar(onBottomAppIconClicked = {})
    }
}

@Preview
@Composable
fun PreviewBottomAppBarIcon() {
    NotesTheme {
        BottomAppBarIcon(
            onClick = { /*TODO*/ },
            icon = Icons.Outlined.Mic,
            description = "Description"
        )
    }
}