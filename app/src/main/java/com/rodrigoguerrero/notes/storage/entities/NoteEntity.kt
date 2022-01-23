package com.rodrigoguerrero.notes.storage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey val id: UUID,
    val title: String? = null,
    val content: String,
    val dateCreated: Long,
    val dateModified: Long,
    val isNoteArchived: Boolean,
    val isNoteDeleted: Boolean,
    val color: Int,
    val isPinned: Boolean
)
