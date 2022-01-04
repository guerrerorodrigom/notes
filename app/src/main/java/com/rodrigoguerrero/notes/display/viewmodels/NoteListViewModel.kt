package com.rodrigoguerrero.notes.display.viewmodels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rodrigoguerrero.notes.display.repository.NoteDisplayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteDisplayRepository: NoteDisplayRepository
) : ViewModel() {

    private val _isListMode = MutableLiveData<Boolean>()
    val isListMode: LiveData<Boolean>
        get() = _isListMode

    private val _listModeIcon = MutableLiveData<ImageVector>()
    val listModeIcon: LiveData<ImageVector>
        get() = _listModeIcon

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _isEmpty = MutableLiveData(false)
    val isEmpty: LiveData<Boolean>
        get() = _isEmpty

    val notes = noteDisplayRepository
        .availableNotes
        .distinctUntilChanged()
        .onStart {
            _isLoading.value = true
        }
        .onEach {
            _isEmpty.value = it.isEmpty()
            _isLoading.value = false
        }

    fun setDisplayMode(isListMode: Boolean) {
        _isListMode.value = isListMode
        _listModeIcon.value = if (isListMode) {
            Icons.Filled.GridView
        } else {
            Icons.Filled.ViewList
        }
    }

    override fun onCleared() {
        super.onCleared()
        noteDisplayRepository.destroy()
    }
}