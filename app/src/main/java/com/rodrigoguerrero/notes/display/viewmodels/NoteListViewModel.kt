package com.rodrigoguerrero.notes.display.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rodrigoguerrero.notes.display.repository.NoteDisplayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteDisplayRepository: NoteDisplayRepository
) : ViewModel() {

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

    override fun onCleared() {
        super.onCleared()
        noteDisplayRepository.destroy()
    }
}