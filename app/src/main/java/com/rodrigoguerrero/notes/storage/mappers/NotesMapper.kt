package com.rodrigoguerrero.notes.storage.mappers

import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.storage.entities.NoteEntity
import java.util.*

internal fun Note.toTextNoteEntity() =
    NoteEntity(
        id = id ?: UUID.randomUUID(),
        title = title,
        content = content,
        dateCreated = createdDate,
        dateModified = modifiedDate,
        isNoteArchived = isArchived,
        isNoteDeleted = isDeleted,
        color = color,
        isPinned = isPinned
    )

internal fun NoteEntity.toNote() =
    Note(
        id = id,
        title = title,
        content = content,
        createdDate = dateCreated,
        modifiedDate = dateModified,
        isArchived = isNoteArchived,
        isDeleted = isNoteDeleted,
        color = color,
        isPinned = isPinned
    )
