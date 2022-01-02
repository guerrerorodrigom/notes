package com.rodrigoguerrero.notes.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rodrigoguerrero.notes.storage.dao.TextNotesDao
import com.rodrigoguerrero.notes.storage.entities.TextNoteEntity

@Database(
    entities = [TextNoteEntity::class],
    version = 1
)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun textNotesDao(): TextNotesDao
}
