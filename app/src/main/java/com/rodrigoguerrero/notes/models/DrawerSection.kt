package com.rodrigoguerrero.notes.models

import com.rodrigoguerrero.notes.R

data class DrawerSection(
    val items: List<DrawerMenuItem>,
    val withDivider: Boolean,
    val title: Int? = null
)

private val MainSection = DrawerSection(
    items = listOf(DrawerMenuItem.AllNotes, DrawerMenuItem.Archive, DrawerMenuItem.Deleted),
    withDivider = true,
    title = R.string.app_name
)

private val NotebooksSection = DrawerSection(
    items = listOf(DrawerMenuItem.Notebooks),
    withDivider = true,
    title = R.string.notebooks
)

private val TagsSection = DrawerSection(
    items = listOf(DrawerMenuItem.Tags),
    withDivider = true
)

private val SettingsSection = DrawerSection(
    items = listOf(DrawerMenuItem.Settings),
    withDivider = false
)

val drawerSections = listOf(MainSection, NotebooksSection, TagsSection, SettingsSection)
