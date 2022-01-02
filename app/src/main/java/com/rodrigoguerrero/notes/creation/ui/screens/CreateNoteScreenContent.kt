package com.rodrigoguerrero.notes.creation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.notes.R

@Composable
fun CreateNoteScreenContent() {
    Surface {

        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            var title by remember { mutableStateOf("") }
            var content by remember { mutableStateOf("") }

            TextField(
                value = title,
                onValueChange = { title = it },
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
                onValueChange = { content = it },
                singleLine = false,
                textStyle = MaterialTheme.typography.subtitle1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(0.dp),
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
}

@Preview
@Composable
fun PreviewCreateNoteScreen() {
    CreateNoteScreenContent()
}