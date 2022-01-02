package com.rodrigoguerrero.notes.common.ui

import androidx.compose.runtime.Composable

interface Screen {
    @Composable
    fun TopAppBarActions()

    @Composable
    fun TopAppBarNavigationIcon()

    @Composable
    fun ScreenTitle()
}
