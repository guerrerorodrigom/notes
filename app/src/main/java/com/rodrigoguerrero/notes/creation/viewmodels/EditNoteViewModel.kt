package com.rodrigoguerrero.notes.creation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.creation.repository.NoteCreationRepository
import com.rodrigoguerrero.notes.creation.repository.NoteOperationStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val textNoteCreationRepository: NoteCreationRepository
) : ViewModel() {

    private val savedNote = textNoteCreationRepository
        .noteOperationStatus
        .filter { it is NoteOperationStatus.Retrieved }
        .map { (it as NoteOperationStatus.Retrieved).note }
        .onEach { _progress.value = false }

    private val _updatedNote = MutableStateFlow<Note?>(null)
    val note = merge(savedNote, _updatedNote)

    private val _progress = MutableLiveData(true)
    val progress: LiveData<Boolean>
        get() = _progress

    private val _areFieldsEnabled = MutableStateFlow(false)
    val areFieldsEnabled: StateFlow<Boolean>
        get() = _areFieldsEnabled

    private val _showWarning = MutableStateFlow(false)
    val showWarning: StateFlow<Boolean>
        get() = _showWarning

    val hasChanged = savedNote
        .combine(_updatedNote) { originalNote, updatedNote ->
            updatedNote != null && originalNote != updatedNote
        }

    private val noChanges = MutableStateFlow(false)
    val saveCompleted = textNoteCreationRepository
        .noteOperationStatus
        .combine(noChanges) { status, saveWithNoChanges ->
            status == NoteOperationStatus.Success || saveWithNoChanges
        }
        .onEach { disableFields() }

    fun getNote(uuid: UUID) {
        viewModelScope.launch {
            textNoteCreationRepository.getNote(uuid)
        }
    }

    fun enableFields() {
        _areFieldsEnabled.value = true
    }

    fun onTitleChanged(note: Note, title: String) {
        _updatedNote.value = note.copy(title = title, modifiedDate = Date().time)
    }

    fun onContentChanged(note: Note, content: String) {
        _updatedNote.value = note.copy(content = content, modifiedDate = Date().time)
    }

    fun save() {
        viewModelScope.launch {
            _updatedNote.value?.let {
                textNoteCreationRepository.upsertTextNote(it)
            } ?: noChanges.emit(true)
        }
    }

    private fun disableFields() {
        _areFieldsEnabled.value = false
    }

    fun showWarning() {
        _showWarning.value = true
    }

    fun hideWarning() {
        _showWarning.value = false
    }
}
