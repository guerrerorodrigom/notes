package com.rodrigoguerrero.notes.creation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.creation.repository.NoteCreationRepository
import com.rodrigoguerrero.notes.creation.repository.NoteOperationStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreateNoteViewModel @Inject constructor(
    private val noteCreationRepository: NoteCreationRepository
) : ViewModel() {
    private val noChanges = MutableStateFlow(false)
    val successState = noteCreationRepository
        .noteOperationStatus
        .combine(noChanges) { status, noChanges ->
            status == NoteOperationStatus.Success || noChanges
        }
        .onEach {
            _title.value = ""
            _content.value = ""
        }

    private val _title = MutableStateFlow("")
    val title: StateFlow<String>
        get() = _title
    private val _content = MutableStateFlow("")
    val content: StateFlow<String>
        get() = _content

    val processing = noteCreationRepository
        .noteOperationStatus
        .map {
            it == NoteOperationStatus.Processing
        }

    fun onTitleChanged(title: String) {
        _title.value = title
    }

    fun onContentChanged(content: String) {
        _content.value = content
    }

    fun createNote() {
        viewModelScope.launch {
            if (_title.value.isNotEmpty() || _content.value.isNotEmpty()) {
                val note = Note(
                    title = _title.value,
                    content = _content.value,
                    createdDate = Date().time,
                    modifiedDate = Date().time,
                    isHidden = false,
                    isArchived = false,
                    id = UUID.randomUUID()
                )
                noteCreationRepository.upsertTextNote(note)
            } else {
                noChanges.emit(true)
            }
        }
    }
}
