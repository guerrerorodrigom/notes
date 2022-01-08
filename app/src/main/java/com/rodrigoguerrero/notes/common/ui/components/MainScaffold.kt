package com.rodrigoguerrero.notes.common.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.Composable

@Composable
fun MainScaffold(
    topBar: @Composable (() -> Unit) = {},
    bottomBar: @Composable (() -> Unit) = {},
    fab: @Composable () -> Unit = {},
    drawer: @Composable (ColumnScope.() -> Unit) = {},
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = bottomBar,
        topBar = topBar,
        content = content,
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = fab,
        isFloatingActionButtonDocked = true,
        drawerContent = drawer,
        scaffoldState = scaffoldState,
    )
}
