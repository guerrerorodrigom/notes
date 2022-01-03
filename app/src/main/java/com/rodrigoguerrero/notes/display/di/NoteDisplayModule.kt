package com.rodrigoguerrero.notes.display.di

import com.rodrigoguerrero.notes.display.repository.NoteDisplayRepository
import com.rodrigoguerrero.notes.display.repository.NoteDisplayRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NoteDisplayModule {
    @Binds
    abstract fun bindNoteDisplayRepository(
        noteDisplayRepository: NoteDisplayRepositoryImpl
    ): NoteDisplayRepository
}
