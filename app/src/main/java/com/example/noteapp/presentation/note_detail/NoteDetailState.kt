package com.example.noteapp.presentation.note_detail

sealed class NoteDetailState {
    object Loading : NoteDetailState()
    object Success : NoteDetailState()
    data class Error(val message: String) : NoteDetailState()
}