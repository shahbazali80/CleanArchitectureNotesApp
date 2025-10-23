package com.example.noteapp.presentation.notes_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.usecase.DeleteNoteUseCase
import com.example.noteapp.domain.usecase.GetAllNotesUseCase
import com.example.noteapp.presentation.utils.showLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
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

    fun loadNotes() {
        viewModelScope.launch {
            getAllNotesUseCase()
                .onStart {
                    _state.value = NotesListState(isLoading = true)
                }
                .catch { e ->
                    _state.value = NotesListState(error = e.message ?: "Something went wrong")
                }
                .collect { notes ->
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