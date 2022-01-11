package com.rodrigoguerrero.notes.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodrigoguerrero.notes.storage.entities.NoteEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes WHERE isNoteArchived = 0 AND isNoteDeleted = 0 ORDER BY dateModified DESC")
    fun getAvailable(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE isNoteArchived = 1 ORDER BY dateModified DESC")
    fun getArchived(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE isNoteDeleted = 1 ORDER BY dateModified DESC")
    fun getDeleted(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id=:id")
    fun getNote(id: UUID): Flow<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)
}
