package com.example.noteapp.domain.usecase

import androidx.lifecycle.LiveData
import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.respository.NoteRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke(): LiveData<List<Note>> {
        return repository.getAllNotes()
    }
}