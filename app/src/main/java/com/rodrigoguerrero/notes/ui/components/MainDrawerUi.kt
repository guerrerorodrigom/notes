package com.rodrigoguerrero.notes.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.models.drawerSections
import com.rodrigoguerrero.notes.ui.dimens.drawerMenuTopSpace
import com.rodrigoguerrero.notes.ui.theme.NotesTheme

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