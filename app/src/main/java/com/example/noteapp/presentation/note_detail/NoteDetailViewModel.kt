package com.example.noteapp.presentation.note_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.repository.NoteRepositoryImpl
import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.respository.NoteRepository
import com.example.noteapp.domain.usecase.InsertNoteUseCase
import com.example.noteapp.domain.usecase.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val insertNoteUseCase: InsertNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,

): ViewModel() {
    private val _state = MutableLiveData(NoteDetailState(isLoading = true))
    val state: LiveData<NoteDetailState> = _state

    fun addNote(note: Note) {
        viewModelScope.launch {
            try {
                _state.value = NoteDetailState(isLoading = true) //❌
                insertNoteUseCase(note)


                _state.value = NoteDetailState(isSuccess = true) //❌
            } catch (e: Exception) {
                _state.value = NoteDetailState(error = e.localizedMessage ?: "Something went wrong")
            }
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            try {
                _state.value = NoteDetailState(isLoading = true)
                updateNoteUseCase(note)
                _state.value = NoteDetailState(isSuccess = true)
            } catch (e: Exception) {
                _state.value = NoteDetailState(error = e.localizedMessage ?: "Something went wrong")
            }
        }
    }
}