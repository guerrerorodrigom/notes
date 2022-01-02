package com.rodrigoguerrero.notes.app.ui.screens

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.rodrigoguerrero.notes.app.navigation.MainDestinations.DEFAULT
import com.rodrigoguerrero.notes.app.navigation.NotesGraph
import com.rodrigoguerrero.notes.app.theme.NotesTheme
import com.rodrigoguerrero.notes.app.ui.components.MainDrawerMenu
import com.rodrigoguerrero.notes.common.ui.Screen
import com.rodrigoguerrero.notes.common.ui.topBarElevation
import com.rodrigoguerrero.notes.creation.viewmodels.CreateNoteViewModel
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
                val createNoteViewModel: CreateNoteViewModel = hiltViewModel()

                var screen: Screen = getScreen(
                    currentRoute,
                    navController,
                    scope,
                    scaffoldState.drawerState,
                    createNoteViewModel
                )

                navController.addOnDestinationChangedListener { controller, destination, arguments ->
                    screen = getScreen(
                        destination.route ?: DEFAULT,
                        navController,
                        scope,
                        scaffoldState.drawerState,
                        createNoteViewModel
                    )
                }

                Scaffold(
                    scaffoldState = scaffoldState,
                    floatingActionButton = { screen.Fab() },
                    isFloatingActionButtonDocked = true,
                    floatingActionButtonPosition = FabPosition.End,
                    topBar = {
                        TopAppBar(
                            elevation = topBarElevation,
                            title = { screen.ScreenTitle() },
                            backgroundColor = MaterialTheme.colors.primarySurface,
                            navigationIcon = { screen.TopAppBarNavigationIcon() },
                            actions = { screen.TopAppBarActions() }
                        )
                    },
                    bottomBar = { screen.BottomAppBar() },
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
                    NotesGraph(
                        navController = navController,
                        createNoteViewModel = createNoteViewModel
                    )
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
