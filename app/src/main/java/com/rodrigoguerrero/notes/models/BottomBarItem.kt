package com.rodrigoguerrero.notes.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AttachFile
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.ui.graphics.vector.ImageVector
import com.rodrigoguerrero.notes.ui.BottomBarDestinations.AUDIO
import com.rodrigoguerrero.notes.ui.BottomBarDestinations.CHECKLIST
import com.rodrigoguerrero.notes.ui.BottomBarDestinations.DOCUMENT
import com.rodrigoguerrero.notes.ui.BottomBarDestinations.PHOTO

sealed class BottomBarItem(val icon: ImageVector, val route: String) {
    object Checklist : BottomBarItem(Icons.Outlined.CheckBox, CHECKLIST)
    object Document : BottomBarItem(Icons.Outlined.AttachFile, DOCUMENT)
    object Audio : BottomBarItem(Icons.Outlined.Mic, AUDIO)
    object Photo : BottomBarItem(Icons.Outlined.PhotoCamera, PHOTO)
}

val bottomBarItems = listOf(
    BottomBarItem.Checklist,
    BottomBarItem.Document,
    BottomBarItem.Photo,
    BottomBarItem.Audio
)
