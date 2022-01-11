package com.rodrigoguerrero.notes.storage.di

import android.content.Context
import androidx.room.Room
import com.rodrigoguerrero.notes.storage.NotesDatabase
import com.rodrigoguerrero.notes.storage.dao.NotesDao
import com.rodrigoguerrero.notes.storage.datasources.NotesDataSource
import com.rodrigoguerrero.notes.storage.datasources.NotesDataSourceImpl
import com.rodrigoguerrero.notes.storage.datastore.NotesDataStoreManager
import com.rodrigoguerrero.notes.storage.datastore.NotesDataStoreManagerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
abstract class StorageAbstractModule {
    @Binds
    abstract fun bindTextNotesDataSource(
        textNotesDataSource: NotesDataSourceImpl
    ): NotesDataSource

    @Binds
    abstract fun bindNotesDataStoreManager(
        notesDataStoreManager: NotesDataStoreManagerImpl
    ): NotesDataStoreManager
}

@Module
@InstallIn(ViewModelComponent::class)
object StorageModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NotesDatabase =
        Room
            .databaseBuilder(
                context,
                NotesDatabase::class.java,
                "notes-database"
            )
            .build()

    @Provides
    fun provideTextNotesDao(database: NotesDatabase): NotesDao = database.notesDao()
}
