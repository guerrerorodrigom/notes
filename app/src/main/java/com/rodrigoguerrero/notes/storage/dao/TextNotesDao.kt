package com.rodrigoguerrero.notes.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodrigoguerrero.notes.storage.entities.TextNoteEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface TextNotesDao {

    @Query("SELECT * FROM text_notes ORDER BY dateModified DESC")
    fun getAll(): Flow<List<TextNoteEntity>>

    @Query("SELECT * FROM text_notes WHERE id=:id")
    fun getNote(id: UUID): Flow<TextNoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: TextNoteEntity)
}
