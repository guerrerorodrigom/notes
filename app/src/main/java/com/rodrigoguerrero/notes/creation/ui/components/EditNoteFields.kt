package com.rodrigoguerrero.notes.creation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.rodrigoguerrero.notes.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun EditNoteFields(
    title: String,
    onTitleChanged: (String) -> Unit,
    content: String,
    onContentChanged: (String) -> Unit,
    readOnlyFields: Boolean,
    scope: CoroutineScope
) {
    Surface {
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            TextField(
                value = title,
                onValueChange = {
                    scope.launch {
                        onTitleChanged(it)
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
                readOnly = readOnlyFields,
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
                        onContentChanged(it)
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
                readOnly = readOnlyFields,
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
