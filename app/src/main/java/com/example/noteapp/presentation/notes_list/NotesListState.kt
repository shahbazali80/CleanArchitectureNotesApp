package com.example.noteapp.presentation.notes_list

import com.example.noteapp.domain.model.Note

sealed class NotesListState {
    object Loading : NotesListState()
    data class Success(val notes: List<Note>) : NotesListState()
    data class Error(val message: String) : NotesListState()
}
