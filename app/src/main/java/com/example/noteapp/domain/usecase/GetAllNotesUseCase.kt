package com.example.noteapp.domain.usecase

import androidx.lifecycle.LiveData
import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.respository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke(): Flow<List<Note>> {
        return repository.getAllNotes()
    }
}