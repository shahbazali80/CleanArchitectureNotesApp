package com.example.noteapp.presentation.note_detail

data class NoteDetailState (
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)//❌