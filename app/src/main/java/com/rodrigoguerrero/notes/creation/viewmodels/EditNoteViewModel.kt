package com.rodrigoguerrero.notes.creation.viewmodels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Unarchive
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.creation.repository.NoteCreationRepository
import com.rodrigoguerrero.notes.creation.repository.NoteOperationStatus
import com.rodrigoguerrero.notes.creation.ui.screens.BottomSheetType
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

    private val _bottomSheetType = MutableStateFlow<BottomSheetType?>(null)
    val bottomSheetType: StateFlow<BottomSheetType?>
        get() = _bottomSheetType

    private val _noteColor = MutableStateFlow(Color.Transparent)
    val noteColor = note.combine(_noteColor) { note, color ->
        if (note != null && note.color != 0) {
            Color(note.color)
        } else {
            color
        }
    }

    val archiveIcon = note
        .map {
            if (it?.isArchived == true) {
                Icons.Filled.Unarchive
            } else {
                Icons.Filled.Archive
            }
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
            isDeleted = false,
            color = _noteColor.value.toArgb()
        )
    }

    fun onContentChanged(note: Note?, content: String) {
        _updatedNote.value = note?.copy(content = content, modifiedDate = Date().time) ?: Note(
            title = "",
            createdDate = Date().time,
            modifiedDate = Date().time,
            content = content,
            isArchived = false,
            isDeleted = false,
            color = _noteColor.value.toArgb()
        )
    }

    fun onArchiveUnarchive(note: Note?) {
        viewModelScope.launch {
            note?.let {
                textNoteCreationRepository.toggleArchiveState(it)
            }
        }
    }

    fun onNoteColorSelected(note: Note?, color: Color) {
        _updatedNote.value = note?.copy(color = color.toArgb()) ?: Note(
            title = "",
            createdDate = Date().time,
            modifiedDate = Date().time,
            content = "",
            isArchived = false,
            isDeleted = false,
            color = color.toArgb()
        )
        _noteColor.value = color
    }

    fun save() {
        viewModelScope.launch {
            _updatedNote.value?.let {
                textNoteCreationRepository.upsertTextNote(it)
            }
        }
    }

    fun setBottomSheetType(bottomSheetType: BottomSheetType) {
        _bottomSheetType.value = bottomSheetType
    }
}
