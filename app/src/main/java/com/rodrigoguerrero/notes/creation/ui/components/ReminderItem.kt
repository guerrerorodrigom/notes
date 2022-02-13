package com.rodrigoguerrero.notes.creation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.notes.common.ui.padding16
import com.rodrigoguerrero.notes.common.ui.padding8
import com.rodrigoguerrero.notes.creation.models.ReminderSuggestion

@Composable
fun ReminderItem(
    reminderSuggestion: ReminderSuggestion,
    modifier: Modifier = Modifier,
    onClick: (ReminderSuggestion) -> Unit,
) {
    Row(
        modifier = modifier
            .clickable { onClick(reminderSuggestion) }
            .padding(horizontal = padding16, vertical = padding8)
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.Schedule,
            contentDescription = "",
            modifier = Modifier
                .padding(end = padding8)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = reminderSuggestion.description,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        if (reminderSuggestion.time.isNotEmpty()) {
            Spacer(modifier = Modifier.weight(1.0f))
            Text(
                text = reminderSuggestion.time,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview
@Composable
fun ReminderItemPreview() {
    val reminderSuggestion = ReminderSuggestion(0L, "Later today", "18:00")
    ReminderItem(reminderSuggestion) {}
}