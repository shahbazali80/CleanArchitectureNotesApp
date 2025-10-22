package com.example.noteapp.presentation.notes_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.usecase.DeleteNoteUseCase
import com.example.noteapp.domain.usecase.GetAllNotesUseCase
import com.example.noteapp.utils.showLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
): ViewModel() {
    private val _state = MutableLiveData(NotesListState(isLoading = true))
    val state: LiveData<NotesListState> = _state

    init {
        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch {
            _state.value = _state.value?.copy(isLoading = true)
            getAllNotesUseCase().observeForever { notes ->
                _state.value = NotesListState(
                    notes = notes,
                    isLoading = false
                )
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