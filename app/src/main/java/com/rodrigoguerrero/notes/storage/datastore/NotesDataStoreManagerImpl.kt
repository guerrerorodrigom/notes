package com.rodrigoguerrero.notes.storage.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

private const val DataStoreSettingsName = "notes-settings"
private val Context.settingsDataStore by preferencesDataStore(DataStoreSettingsName)

class NotesDataStoreManagerImpl @Inject constructor(
    @ApplicationContext context: Context
) : NotesDataStoreManager {

    private val scope: CoroutineScope = CoroutineScope(
        Dispatchers.IO + SupervisorJob()
    )
    private val settings = context.settingsDataStore
    private val isListModePreference = booleanPreferencesKey(isListModeSettings)

    override val isListMode: Flow<Boolean> = settings.data
        .map { prefs ->
            prefs[isListModePreference] ?: true
        }
        .shareIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(),
            replay = 1
        )

    override suspend fun setListMode(isListMode: Boolean) {
        settings.edit { prefs ->
            prefs[isListModePreference] = isListMode
        }
    }

    override fun dispose() {
        scope.cancel()
    }
}
