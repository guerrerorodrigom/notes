package com.rodrigoguerrero.notes.creation.di

import com.rodrigoguerrero.notes.creation.repository.NoteCreationRepository
import com.rodrigoguerrero.notes.creation.repository.NoteCreationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NoteCreationModule {
    @Binds
    abstract fun bindNoteCreationRepository(
        noteCreationRepository: NoteCreationRepositoryImpl
    ): NoteCreationRepository
}
