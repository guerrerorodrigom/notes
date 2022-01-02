package com.rodrigoguerrero.notes.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.notes.app.ui.models.drawerSections
import com.rodrigoguerrero.notes.common.ui.drawerMenuTopSpace
import com.rodrigoguerrero.notes.app.theme.NotesTheme

@Composable
fun MainDrawerMenu(
    onNavigateFromMenu: (String) -> Unit,
    currentRoute: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Spacer(Modifier.height(drawerMenuTopSpace))
        for (section in drawerSections) {
            DrawerSection(
                currentRoute = currentRoute,
                drawerSection = section,
                onItemClicked = { onNavigateFromMenu(it) })
        }
    }
}

@Preview
@Composable
fun PreviewMainDrawerMenu() {
    NotesTheme {
        MainDrawerMenu(
            onNavigateFromMenu = {},
            currentRoute = "allnotes",
        )
    }
}