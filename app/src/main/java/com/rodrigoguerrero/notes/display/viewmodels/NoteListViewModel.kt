package com.rodrigoguerrero.notes.display.viewmodels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.notes.display.repository.NoteDisplayRepository
import com.rodrigoguerrero.notes.storage.datastore.NotesDataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

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

    val notes = noteDisplayRepository
        .availableNotes
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

    fun setDisplayMode(isListMode: Boolean) {
        viewModelScope.launch {
            notesDataStoreManager.setListMode(isListMode)
        }
    }

    override fun onCleared() {
        super.onCleared()
        noteDisplayRepository.destroy()
        notesDataStoreManager.dispose()
    }
}