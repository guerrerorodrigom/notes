package com.rodrigoguerrero.notes.display.viewmodels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.notes.R
import com.rodrigoguerrero.notes.app.navigation.MainDestinations.ALL_NOTES
import com.rodrigoguerrero.notes.app.navigation.MainDestinations.ARCHIVE
import com.rodrigoguerrero.notes.app.navigation.MainDestinations.DELETED
import com.rodrigoguerrero.notes.common.models.Note
import com.rodrigoguerrero.notes.display.repository.NoteDisplayRepository
import com.rodrigoguerrero.notes.storage.datastore.NotesDataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteDisplayRepository: NoteDisplayRepository,
    private val notesDataStoreManager: NotesDataStoreManager
) : ViewModel() {

    private val _listModeIcon = MutableLiveData<ImageVector>()
    val listModeIcon: LiveData<ImageVector>
        get() = _listModeIcon

    private val _isEmpty = MutableLiveData(false)
    val isEmpty: LiveData<Boolean>
        get() = _isEmpty

    private val _noteDisplayType = MutableStateFlow<NoteDisplayType>(NoteDisplayType.AllNotes)

    val title = _noteDisplayType
        .map { displayType ->
            when (displayType) {
                NoteDisplayType.DeletedNotes -> R.string.deleted
                NoteDisplayType.ArchivedNotes -> R.string.archive
                else -> R.string.main_top_bar_title
            }
        }

    val selectedMenu = _noteDisplayType
        .map { displayType ->
            when (displayType) {
                NoteDisplayType.DeletedNotes -> DELETED
                NoteDisplayType.ArchivedNotes -> ARCHIVE
                NoteDisplayType.AllNotes -> ALL_NOTES
            }
        }

    private val deletedNotes = noteDisplayRepository
        .deletedNotes
        .distinctUntilChanged()

    private val archivedNotes = noteDisplayRepository
        .archivedNotes
        .distinctUntilChanged()

    private val availableNotes = noteDisplayRepository
        .availableNotes
        .distinctUntilChanged()

    private val _pinnedNotes = noteDisplayRepository.pinnedNotes.distinctUntilChanged()

    val pinnedNotes = _noteDisplayType
        .map { displayType ->
            when (displayType) {
                NoteDisplayType.AllNotes -> _pinnedNotes
                else -> flowOf(listOf())
            }
        }
        .flatMapLatest { it }
        .distinctUntilChanged()

    val notes = _noteDisplayType
        .map { displayType ->
            when (displayType) {
                NoteDisplayType.DeletedNotes -> deletedNotes
                NoteDisplayType.ArchivedNotes -> archivedNotes
                else -> availableNotes
            }
        }
        .flatMapLatest { it }
        .distinctUntilChanged()
        .onEach {
            _isEmpty.value = it.isEmpty()
        }

    val isListMode: SharedFlow<Boolean> = notesDataStoreManager
        .isListMode
        .onEach { isListMode ->
            _listModeIcon.value = if (isListMode) {
                Icons.Filled.GridView
            } else {
                Icons.Filled.ViewList
            }
        }
        .shareIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            replay = 1
        )

    val isLoading: SharedFlow<Boolean> = notes
        .combine(isListMode) { _, _ -> false }
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)

    fun toggleListMode() {
        viewModelScope.launch {
            notesDataStoreManager.toggleListMode()
        }
    }

    fun handleNoteDisplayType(destination: String): Boolean {
        return when (destination) {
            ALL_NOTES -> {
                _noteDisplayType.value = NoteDisplayType.AllNotes
                true
            }
            ARCHIVE -> {
                _noteDisplayType.value = NoteDisplayType.ArchivedNotes
                true
            }
            DELETED -> {
                _noteDisplayType.value = NoteDisplayType.DeletedNotes
                true
            }
            else -> false
        }
    }

    override fun onCleared() {
        super.onCleared()
        noteDisplayRepository.destroy()
        notesDataStoreManager.dispose()
    }
}