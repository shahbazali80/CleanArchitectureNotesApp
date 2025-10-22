package com.example.noteapp.domain.respository

import androidx.lifecycle.LiveData
import com.example.noteapp.domain.model.Note

interface NoteRepository {
    fun getAllNotes(): LiveData<List<Note>>
    suspend fun insertNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
}