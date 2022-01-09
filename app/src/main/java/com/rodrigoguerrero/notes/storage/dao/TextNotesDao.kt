package com.rodrigoguerrero.notes.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodrigoguerrero.notes.storage.entities.NoteEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface TextNotesDao {

    @Query("SELECT * FROM notes ORDER BY dateModified DESC")
    fun getAll(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id=:id")
    fun getNote(id: UUID): Flow<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)
}
