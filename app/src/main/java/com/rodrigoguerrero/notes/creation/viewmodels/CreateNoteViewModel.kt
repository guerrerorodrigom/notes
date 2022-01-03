package com.rodrigoguerrero.notes.creation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.creation.repository.NoteCreationRepository
import com.rodrigoguerrero.notes.creation.repository.NoteCreationStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreateNoteViewModel @Inject constructor(
    private val noteCreationRepository: NoteCreationRepository
) : ViewModel() {

    val successState = noteCreationRepository
        .noteCreationStatus
        .map { it == NoteCreationStatus.Success }

    val title = MutableStateFlow("")
    val content = MutableStateFlow("")
    val isFabEnabled = content.map { it.isNotEmpty() }
    val processing = noteCreationRepository
        .noteCreationStatus
        .map {
            it == NoteCreationStatus.Processing
        }

    fun createNote() {
        viewModelScope.launch {
            val note = Note(
                title = title.value,
                content = content.value, // TODO: can i use flows here?
                createdDate = Date().time,
                modifiedDate = Date().time,
                isHidden = false,
                isArchived = false,
                id = UUID.randomUUID()
            )
            noteCreationRepository.createTextNote(note)
        }
    }
}