package com.rodrigoguerrero.notes.display.viewmodels

sealed class NoteDisplayType {
    object AllNotes : NoteDisplayType()
    object DeletedNotes : NoteDisplayType()
    object ArchivedNotes : NoteDisplayType()
}
