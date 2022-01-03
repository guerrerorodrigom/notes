package com.rodrigoguerrero.notes.creation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.creation.viewmodels.CreateNoteViewModel
import kotlinx.coroutines.launch

@Composable
fun CreateNoteScreenContent(
    viewModel: CreateNoteViewModel,
    onNavigateBack: () -> Unit
) {
    val showProgress by viewModel.processing.collectAsState(false)
    val isNoteSaved by viewModel.successState.collectAsState(false)

    if (isNoteSaved) {
        onNavigateBack()
    }

    Surface {
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            val title by viewModel.title.collectAsState()
            val content by viewModel.content.collectAsState()
            val scope = rememberCoroutineScope()

            TextField(
                value = title,
                onValueChange = {
                    scope.launch {
                        viewModel.title.value = it
                    }
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.h6,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        text = stringResource(R.string.note_title_placeholder),
                        style = MaterialTheme.typography.h6
                    )
                },
            )

            TextField(
                value = content,
                onValueChange = {
                    scope.launch {
                        viewModel.content.value = it
                    }
                },
                singleLine = false,
                textStyle = MaterialTheme.typography.subtitle1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(0.dp)
                    .navigationBarsWithImePadding(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        text = stringResource(R.string.note_content_placeholder),
                        style = MaterialTheme.typography.subtitle1
                    )
                },
            )
        }
    }
    if (showProgress) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(0.75f)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun PreviewCreateNoteScreen() {
    CreateNoteScreenContent(hiltViewModel()) {}
}