package com.example.noteapp.presentation.note_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.usecase.InsertNoteUseCase
import com.example.noteapp.domain.usecase.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val insertNoteUseCase: InsertNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,

): ViewModel() {
    private val _state = MutableStateFlow(NoteDetailState(isLoading = true))
    val state: StateFlow<NoteDetailState> = _state

    fun addNote(note: Note) {
        viewModelScope.launch {
            try {
                //_state.value = NoteDetailState(isLoading = true) //❌
                _state.update { it.copy(isLoading = true, isSuccess = false, error = null) }
                val id = insertNoteUseCase(note)
                if (id > 0) {
                    _state.update { it.copy(isLoading = false, isSuccess = true) }
                } else {
                    _state.update { it.copy(isLoading = false, isSuccess = false, error = "Insert failed") }
                }

                //_state.value = NoteDetailState(isSuccess = true) //❌
            } catch (e: Exception) {
                _state.update {
                    it.copy(isLoading = false, isSuccess = false, error = e.message ?: "Something went wrong")
                }
            }
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            try {
                //_state.value = NoteDetailState(isLoading = true)
                _state.update { it.copy(isLoading = true, isSuccess = false, error = null) }
                updateNoteUseCase(note)
                //_state.value = NoteDetailState(isSuccess = true)
                _state.update { it.copy(isLoading = false, isSuccess = true) }
            } catch (e: Exception) {
                //_state.value = NoteDetailState(error = e.localizedMessage ?: "Something went wrong")
                _state.update {
                    it.copy(isLoading = false, isSuccess = false, error = e.message ?: "Something went wrong")
                }
            }
        }
    }
}