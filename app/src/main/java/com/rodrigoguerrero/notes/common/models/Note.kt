package com.rodrigoguerrero.notes.common.models

import java.util.*

data class Note(
    val id: UUID,
    val title: String? = null,
    val content: String,
    val createdDate: Long,
    val modifiedDate: Long,
    val isDeleted: Boolean,
    val isArchived: Boolean
)
