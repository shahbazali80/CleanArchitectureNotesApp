package com.example.noteapp.presentation.notes_list

import com.example.noteapp.domain.model.Note

data class NotesListState(
    val notes: List<Note> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
