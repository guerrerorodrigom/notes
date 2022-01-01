package com.rodrigoguerrero.notes.ui.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.ui.MainDestinations.DEFAULT
import com.rodrigoguerrero.notes.ui.SecondaryDestinations
import com.rodrigoguerrero.notes.ui.components.CreateNoteTopBarUi
import com.rodrigoguerrero.notes.ui.components.MainBottomAppBar
import com.rodrigoguerrero.notes.ui.components.MainDrawerMenu
import com.rodrigoguerrero.notes.ui.components.MainTopBar
import com.rodrigoguerrero.notes.ui.theme.NotesTheme
import kotlinx.coroutines.CoroutineScope
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
                val currentRoute = navBackStackEntry?.destination?.route ?: DEFAULT

                Scaffold(
                    scaffoldState = scaffoldState,
                    floatingActionButton = {
                        if (navController.previousBackStackEntry == null) {
                            FloatingActionButton(
                                onClick = { navController.navigate(SecondaryDestinations.CREATE_TEXT_NOTE) },
                                contentColor = MaterialTheme.colors.onSurface
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.AddCircleOutline,
                                    contentDescription = stringResource(
                                        R.string.create_new_note
                                    )
                                )
                            }
                        }
                    },
                    isFloatingActionButtonDocked = true,
                    floatingActionButtonPosition = FabPosition.End,
                    topBar = {
                        RenderTopAppBar(
                            currentDestination = currentRoute,
                            navController = navController,
                            scope = scope,
                            drawerState = scaffoldState.drawerState
                        )
                    },
                    bottomBar = {
                        if (navController.previousBackStackEntry == null) {
                            MainBottomAppBar(onBottomAppIconClicked = {})
                        }
                    },
                    drawerContent = {
                        MainDrawerMenu(
                            onNavigateFromMenu = { destination ->
                                navController.navigate(route = destination) {
                                    popUpTo(currentRoute) { inclusive = true }
                                }
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

@Composable
private fun RenderTopAppBar(
    currentDestination: String,
    navController: NavHostController,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    when (currentDestination) {
        SecondaryDestinations.CREATE_TEXT_NOTE -> CreateNoteTopBarUi {
            navController.popBackStack()
        }
        else -> MainTopBar(
            onNavigationIconClicked = {
                scope.launch {
                    drawerState.open()
                }
            },
            onSortNotesClicked = { /*TODO*/ },
            onListGridClicked = { /*TODO*/ },
            onMoreClicked = {}
        )
    }
}

@Preview
@Composable
fun PreviewNotesApp() {
    NotesApp()
}