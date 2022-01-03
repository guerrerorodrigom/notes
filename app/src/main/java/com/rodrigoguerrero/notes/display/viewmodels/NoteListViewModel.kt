package com.rodrigoguerrero.notes.display.viewmodels

import androidx.lifecycle.ViewModel
import com.rodrigoguerrero.notes.display.repository.NoteDisplayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteDisplayRepository: NoteDisplayRepository
) : ViewModel() {

    val notes = noteDisplayRepository
        .availableNotes
        .distinctUntilChanged()

    override fun onCleared() {
        super.onCleared()
        noteDisplayRepository.destroy()
    }
}