package com.rodrigoguerrero.notes.creation.viewmodels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Unarchive
import androidx.compose.ui.graphics.vector.ImageVector
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

    val archiveIcon: Flow<ImageVector>
        get() = note
            .map {
                if (it?.isArchived == true) {
                    Icons.Filled.Unarchive
                } else {
                    Icons.Filled.Archive
                }
            }

    private val noChanges = MutableStateFlow(false)
    val saveCompleted = textNoteCreationRepository
        .noteOperationStatus
        .combine(noChanges) { status, saveWithNoChanges ->
            status == NoteOperationStatus.Success || saveWithNoChanges
        }

    val archiveNote: Flow<NoteOperationStatus> = textNoteCreationRepository
        .noteOperationStatus
        .filter { it is NoteOperationStatus.Archived || it is NoteOperationStatus.Unarchived }

    fun getNote(uuid: UUID) {
        viewModelScope.launch {
            textNoteCreationRepository.getNote(uuid)
        }
    }

    fun onTitleChanged(note: Note?, title: String) {
        _updatedNote.value = note?.copy(title = title, modifiedDate = Date().time) ?: Note(
            title = title,
            createdDate = Date().time,
            modifiedDate = Date().time,
            content = "",
            isArchived = false,
            isDeleted = false
        )
    }

    fun onContentChanged(note: Note?, content: String) {
        _updatedNote.value = note?.copy(content = content, modifiedDate = Date().time) ?: Note(
            title = "",
            createdDate = Date().time,
            modifiedDate = Date().time,
            content = content,
            isArchived = false,
            isDeleted = false
        )
    }

    fun onArchiveUnarchive(note: Note?) {
        viewModelScope.launch {
            note?.let {
                textNoteCreationRepository.toggleArchiveState(it)
            }
        }
    }

    fun save() {
        viewModelScope.launch {
            _updatedNote.value?.let {
                textNoteCreationRepository.upsertTextNote(it)
            } ?: noChanges.emit(true)
        }
    }
}
