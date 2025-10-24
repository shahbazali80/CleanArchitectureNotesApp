package com.example.noteapp.presentation.note_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.usecase.InsertNoteUseCase
import com.example.noteapp.domain.usecase.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val insertNoteUseCase: InsertNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,

): ViewModel() {
    private val _state = MutableSharedFlow<NoteDetailState>(replay = 1)
    val state: SharedFlow<NoteDetailState> = _state.asSharedFlow()

    fun addNote(note: Note) {
        viewModelScope.launch {
            try {
                _state.emit(NoteDetailState.Loading)
                val id = insertNoteUseCase(note)
                if (id > 0) {
                    _state.emit(NoteDetailState.Success)
                } else {
                    _state.emit(NoteDetailState.Error("Insert failed"))
                }
            } catch (e: Exception) {
                _state.emit(NoteDetailState.Error(e.message ?: "Something went wrong"))
            }
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            try {
                _state.emit(NoteDetailState.Loading)
                val id = updateNoteUseCase(note)
                if (id > 0) {
                    _state.emit(NoteDetailState.Success)
                } else {
                    _state.emit(NoteDetailState.Error("Update failed"))
                }
            } catch (e: Exception) {
                _state.emit(NoteDetailState.Error(e.message ?: "Something went wrong"))
            }
        }
    }
}