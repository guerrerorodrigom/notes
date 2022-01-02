package com.rodrigoguerrero.notes.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.notes.app.ui.models.DrawerSection
import com.rodrigoguerrero.notes.app.ui.models.drawerSections
import com.rodrigoguerrero.notes.common.ui.padding16
import com.rodrigoguerrero.notes.app.theme.NotesTheme

@Composable
fun DrawerSection(
    currentRoute: String,
    drawerSection: DrawerSection,
    onItemClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        drawerSection.title?.let { title ->
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(start = padding16, top = padding16, bottom = padding16)
            )
        }

        for (item in drawerSection.items) {
            DrawerItem(
                drawerMenuItem = item,
                isSelected = item.route == currentRoute,
                onItemClicked = { onItemClicked(item.route) })
        }

        if (drawerSection.withDivider) {
            Divider()
        }
    }
}

@Preview
@Composable
fun PreviewDrawerSection() {
    NotesTheme {
        DrawerSection(
            currentRoute = drawerSections[0].items[0].route,
            drawerSection = drawerSections[0],
            onItemClicked = {}
        )
    }
}

@Preview
@Composable
fun PreviewDrawerWithTitleSection() {
    NotesTheme {
        DrawerSection(
            currentRoute = "",
            drawerSection = drawerSections[1],
            onItemClicked = {}
        )
    }
}