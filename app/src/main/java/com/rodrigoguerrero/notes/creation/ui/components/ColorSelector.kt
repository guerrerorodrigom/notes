package com.rodrigoguerrero.notes.creation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.FormatColorReset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.common.ui.colorSelectorBorderSize
import com.rodrigoguerrero.notes.common.ui.colorSelectorSize
import com.rodrigoguerrero.notes.common.ui.padding16
import com.rodrigoguerrero.notes.common.ui.padding8

private val darkThemeNoteColors = listOf(
    Color.Transparent,
    Color(0xFF5c2b29),
    Color(0xFF614A19),
    Color(0xFF635d19),
    Color(0xFF345920),
    Color(0xFF16504B),
    Color(0xFF2D555E),
    Color(0xFF1E3A5F),
    Color(0xFF42275E),
    Color(0xFF5B2245),
    Color(0xFF442F19),
    Color(0xFF3C3F43)
)

private val noteColors = listOf(
    Color.Transparent,
    Color(0xFFf28b82),
    Color(0xFFfbbc04),
    Color(0xFFfff475),
    Color(0xFFccff90),
    Color(0xFFa7ffeb),
    Color(0xFFcbf0f8),
    Color(0xFFaecbfa),
    Color(0xFFfdcfe8),
    Color(0xFFfdcfe8),
    Color(0xFFe6c9a8),
    Color(0xFFe8eaed)
)

private val selectedBorderColor = Color(0xFFa142f4)

@ExperimentalMaterialApi
@Composable
fun ColorSelectorBottomSheet(
    selectedColor: Color,
    onColorSelected: (Color) -> Unit
) {
    Column(
        modifier = Modifier
            .background(selectedColor)
            .padding(padding16)
    ) {
        Text(
            text = stringResource(R.string.note_color),
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.padding(bottom = padding16)
        )
        ColorSelectorRow(selectedColor, onColorSelected)
    }
}

@Composable
fun ColorSelectorRow(
    selectedColor: Color,
    onColorSelected: (Color) -> Unit
) {
    LazyRow {
        itemsIndexed(noteColors) { index, color ->
            ColorSelector(
                color = color,
                isSelected = color == selectedColor,
                onColorClicked = onColorSelected,
                isEmpty = index == 0 && color != selectedColor
            )
        }
    }
}

@Composable
fun ColorSelector(
    color: Color,
    isSelected: Boolean,
    isEmpty: Boolean,
    onColorClicked: (Color) -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
            .padding(padding8)
    ) {
        var modifier = Modifier
            .size(colorSelectorSize)
            .clip(CircleShape)
            .background(color)

        if (isSelected) {
            modifier = modifier
                .border(
                    border = BorderStroke(
                        width = colorSelectorBorderSize,
                        color = selectedBorderColor
                    ),
                    shape = CircleShape
                )
        }

        IconButton(
            modifier = modifier,
            onClick = { onColorClicked(color) }
        ) {
            val icon = when {
                isEmpty -> Icons.Outlined.FormatColorReset
                isSelected -> Icons.Filled.Check
                else -> null
            }
            icon?.let {
                Icon(imageVector = it, contentDescription = "", tint = selectedBorderColor)
            }
        }
    }
}

@Preview
@Composable
fun PreviewColorSelector() {
    ColorSelector(Color.Blue, false, false) {}
}

@Preview
@Composable
fun PreviewColorSelectorSelected() {
    ColorSelector(Color.Green, true, false) {}
}

@Preview
@Composable
fun PreviewColorRow() {
    ColorSelectorRow(onColorSelected = {}, selectedColor = Color.Transparent)
}