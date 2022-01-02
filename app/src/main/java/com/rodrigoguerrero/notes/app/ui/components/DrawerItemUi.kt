package com.rodrigoguerrero.notes.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.notes.app.ui.models.DrawerMenuItem
import com.rodrigoguerrero.notes.common.ui.drawerMenuItemRoundedCorner
import com.rodrigoguerrero.notes.common.ui.padding16

@Composable
fun DrawerItem(
    drawerMenuItem: DrawerMenuItem,
    isSelected: Boolean,
    onItemClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onItemClicked() }
            .background(
                color = if (isSelected) {
                    MaterialTheme.colors.secondary.copy(alpha = 0.25f)
                } else {
                    Color.Transparent
                },
                shape = if (isSelected) {
                    RoundedCornerShape(
                        topEnd = drawerMenuItemRoundedCorner,
                        bottomEnd = drawerMenuItemRoundedCorner
                    )
                } else {
                    RectangleShape
                }
            )
            .padding(padding16)
            .fillMaxWidth()

    ) {
        Icon(
            imageVector = drawerMenuItem.icon,
            tint = LocalContentColor.current.copy(alpha = ContentAlpha.medium),
            contentDescription = stringResource(drawerMenuItem.title),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = padding16)
        )
        Text(
            text = stringResource(drawerMenuItem.title),
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview
@Composable
fun PreviewUnselectedDrawerItem() {
    DrawerItem(
        drawerMenuItem = DrawerMenuItem.AllNotes,
        onItemClicked = {},
        isSelected = false
    )
}

@Preview
@Composable
fun PreviewSelectedDrawerItem() {
    DrawerItem(
        drawerMenuItem = DrawerMenuItem.Archive,
        onItemClicked = {},
        isSelected = true
    )
}