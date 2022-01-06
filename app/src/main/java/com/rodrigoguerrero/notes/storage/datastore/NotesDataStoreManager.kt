package com.rodrigoguerrero.notes.storage.datastore

import kotlinx.coroutines.flow.Flow

interface NotesDataStoreManager {
    val isListMode: Flow<Boolean>

    suspend fun toggleListMode()
    fun dispose()
}
