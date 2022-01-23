package com.rodrigoguerrero.notes.creation.viewmodels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material.icons.filled.Unarchive
import androidx.compose.material.icons.outlined.PushPin
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
    private val noteCreationRepository: NoteCreationRepository
) : ViewModel() {

    private val savedNote = noteCreationRepository
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

    val pinIcon = note
        .map {
            if (it?.isPinned == true) {
                Icons.Filled.PushPin
            } else {
                Icons.Outlined.PushPin
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

    private val _archiveNote = MutableStateFlow<Boolean?>(null)
    val archiveNote: Flow<Boolean?> = _archiveNote
        .filterNotNull()
        .distinctUntilChanged()

    fun getNote(uuid: UUID) {
        viewModelScope.launch {
            noteCreationRepository.getNote(uuid)
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
            color = _noteColor.value.toArgb(),
            isPinned = false
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
            color = _noteColor.value.toArgb(),
            isPinned = false
        )
    }

    fun onArchiveUnarchive(note: Note?) {
        _updatedNote.value = note?.copy(isArchived = !note.isArchived)
        note?.let {
            _archiveNote.value = _archiveNote.value?.let { !it } ?: true
        }
    }

    fun onPinUnpin(note: Note?) {
        _updatedNote.value = note?.copy(isPinned = !note.isPinned)
    }

    fun onNoteColorSelected(note: Note?, color: Color) {
        _updatedNote.value = note?.copy(color = color.toArgb()) ?: Note(
            title = "",
            createdDate = Date().time,
            modifiedDate = Date().time,
            content = "",
            isArchived = false,
            isDeleted = false,
            color = color.toArgb(),
            isPinned = false
        )
        _noteColor.value = color
    }

    fun save() {
        viewModelScope.launch {
            _updatedNote.value?.let {
                noteCreationRepository.upsertTextNote(it)
            }
        }
    }

    fun setBottomSheetType(bottomSheetType: BottomSheetType) {
        _bottomSheetType.value = bottomSheetType
    }
}
