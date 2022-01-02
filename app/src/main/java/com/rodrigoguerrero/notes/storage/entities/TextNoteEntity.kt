package com.rodrigoguerrero.notes.storage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "text_notes")
data class TextNoteEntity(
    @PrimaryKey val id: UUID,
    val title: String? = null,
    val content: String,
    val dateCreated: Long,
    val dateModified: Long,
    val isNoteArchived: Boolean,
    val isNoteHidden: Boolean
)
