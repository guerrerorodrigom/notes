package com.rodrigoguerrero.notes.creation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.common.ui.padding16
import com.rodrigoguerrero.notes.common.ui.padding8
import com.rodrigoguerrero.notes.creation.models.ReminderSuggestion

@Composable
fun ReminderSelectorBottomSheet(
    reminderSuggestions: List<ReminderSuggestion>,
    selectedColor: Color,
    onShowDatePicker: (ReminderSuggestion) -> Unit,
    onSuggestionSelected: (ReminderSuggestion) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .background(selectedColor)
            .padding(vertical = padding8)
    ) {
        items(reminderSuggestions) {
            ReminderItem(
                reminderSuggestion = it,
                onClick = onSuggestionSelected
            )
        }

        item {
            val reminderSuggestion =
                ReminderSuggestion(0L, stringResource(R.string.choose_date_time))
            ReminderItem(
                reminderSuggestion = reminderSuggestion,
                onClick = onShowDatePicker
            )
        }
    }
}

@Preview
@Composable
fun ReminderSelectorPreview() {
    val laterToday = ReminderSuggestion(0L, "Later Today", "18:00")
    val tomorrowMorning = ReminderSuggestion(0L, "Tomorrow morning", "08:00")
    val sundayMorning = ReminderSuggestion(0L, "Sunday morning", "Sun 08:00")
    val suggestions = listOf(laterToday, tomorrowMorning, sundayMorning)

    ReminderSelectorBottomSheet(
        reminderSuggestions = suggestions,
        selectedColor = Color.White,
        onShowDatePicker = {},
        onSuggestionSelected = {})
}