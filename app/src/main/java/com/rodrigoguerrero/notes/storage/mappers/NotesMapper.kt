package com.rodrigoguerrero.notes.storage.mappers

import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.storage.entities.TextNoteEntity
import java.util.*

internal fun Note.toTextNoteEntity() =
    TextNoteEntity(
        id = id ?: UUID.randomUUID(),
        title = title,
        content = content,
        dateCreated = createdDate,
        dateModified = modifiedDate,
        isNoteArchived = isArchived,
        isNoteHidden = isDeleted
    )

internal fun TextNoteEntity.toNote() =
    Note(
        id = id,
        title = title,
        content = content,
        createdDate = dateCreated,
        modifiedDate = dateModified,
        isArchived = isNoteArchived,
        isDeleted = isNoteHidden
    )
