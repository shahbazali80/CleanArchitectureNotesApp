package com.example.noteapp.presentation.notes_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.usecase.DeleteNoteUseCase
import com.example.noteapp.domain.usecase.GetAllNotesUseCase
import com.example.noteapp.presentation.utils.showLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
): ViewModel() {
    private val _state = MutableSharedFlow<NotesListState>(replay = 1)
    val state: SharedFlow<NotesListState> = _state.asSharedFlow()

    init {
        loadNotes()
    }

    fun loadNotes() {
        viewModelScope.launch {
            getAllNotesUseCase()
                .onStart {
                    _state.emit(NotesListState.Loading)
                }
                .catch { e ->
                    _state.emit(NotesListState.Error(e.message ?: "Something went wrong"))
                }
                .collect { notes ->
                    _state.emit(NotesListState.Success(notes))
                }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            try {
                deleteNoteUseCase(note)
            } catch (e: Exception) {
                showLog("NotesListViewModel12345", e.message.toString())
            }
        }
    }
}