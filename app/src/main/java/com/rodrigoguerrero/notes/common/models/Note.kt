package com.rodrigoguerrero.notes.common.models

import java.util.*

data class Note(
    val id: UUID? = null,
    val title: String? = null,
    val content: String,
    val createdDate: Long,
    val modifiedDate: Long,
    val isDeleted: Boolean,
    val isArchived: Boolean,
    val color: Int,
    val isPinned: Boolean
)
