package com.rodrigoguerrero.notes.models

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.rodrigoguerrero.notes.ui.MainDestinations.ALL_NOTES
import com.rodrigoguerrero.notes.ui.MainDestinations.ARCHIVE
import com.rodrigoguerrero.notes.ui.MainDestinations.DELETED
import com.rodrigoguerrero.notes.ui.MainDestinations.NOTEBOOKS
import com.rodrigoguerrero.notes.ui.MainDestinations.SETTINGS
import com.rodrigoguerrero.notes.ui.MainDestinations.TAGS
import com.rodrigoguerrero.notes.R

sealed class DrawerMenuItem(@StringRes val title: Int, val icon: ImageVector, val route: String) {
    object AllNotes : DrawerMenuItem(R.string.all_notes, Icons.Filled.List, ALL_NOTES)
    object Archive : DrawerMenuItem(R.string.archive, Icons.Filled.Folder, ARCHIVE)
    object Deleted : DrawerMenuItem(R.string.deleted, Icons.Filled.Delete, DELETED)
    object Tags : DrawerMenuItem(R.string.tags, Icons.Filled.Label, TAGS)
    object Settings : DrawerMenuItem(R.string.settings, Icons.Filled.Settings, SETTINGS)
    object Notebooks : DrawerMenuItem(R.string.your_notebooks, Icons.Filled.Book, NOTEBOOKS)
}
