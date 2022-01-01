package com.rodrigoguerrero.notes.ui.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.ui.MainDestinations.ALL_NOTES
import com.rodrigoguerrero.notes.ui.components.MainBottomAppBar
import com.rodrigoguerrero.notes.ui.components.MainDrawerMenu
import com.rodrigoguerrero.notes.ui.components.MainTopBar
import com.rodrigoguerrero.notes.ui.theme.NotesTheme
import kotlinx.coroutines.launch

@Composable
fun NotesApp() {
    NotesTheme {
        ProvideWindowInsets {
            Surface(color = MaterialTheme.colors.background) {
                val navController = rememberNavController()
                val scope = rememberCoroutineScope()
                val scaffoldState = rememberScaffoldState()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route ?: ALL_NOTES

                Scaffold(
                    scaffoldState = scaffoldState,
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { /*TODO*/ },
                            contentColor = MaterialTheme.colors.onSurface
                        ) {
                            Icon(
                                imageVector = Icons.Filled.AddCircleOutline,
                                contentDescription = stringResource(
                                    R.string.create_new_note
                                )
                            )
                        }
                    },
                    isFloatingActionButtonDocked = true,
                    floatingActionButtonPosition = FabPosition.End,
                    topBar = {
                        MainTopBar(
                            onNavigationIconClicked = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            },
                            onSortNotesClicked = { /*TODO*/ },
                            onListGridClicked = { /*TODO*/ }) {
                        }
                    },
                    bottomBar = {
                        MainBottomAppBar(onBottomAppIconClicked = {})
                    },
                    drawerContent = {
                        MainDrawerMenu(
                            onNavigateFromMenu = { destination ->
                                navController.navigate(destination)
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                }
                            },
                            currentRoute = currentRoute,
                        )
                    }
                ) {
                    NotesGraph(navController = navController)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewNotesApp() {
    NotesApp()
}