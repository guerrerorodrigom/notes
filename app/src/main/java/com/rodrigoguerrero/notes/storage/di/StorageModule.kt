package com.rodrigoguerrero.notes.storage.di

import android.content.Context
import androidx.room.Room
import com.rodrigoguerrero.notes.storage.NotesDatabase
import com.rodrigoguerrero.notes.storage.dao.TextNotesDao
import com.rodrigoguerrero.notes.storage.datasources.TextNotesDataSource
import com.rodrigoguerrero.notes.storage.datasources.TextNotesDataSourceImpl
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
        textNotesDataSource: TextNotesDataSourceImpl
    ): TextNotesDataSource
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
    fun provideTextNotesDao(database: NotesDatabase): TextNotesDao = database.textNotesDao()
}
