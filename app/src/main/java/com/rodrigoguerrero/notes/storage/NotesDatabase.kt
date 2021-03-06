package com.rodrigoguerrero.notes.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rodrigoguerrero.notes.storage.dao.NotesDao
import com.rodrigoguerrero.notes.storage.entities.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}
