package com.example.noteapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.noteapp.data.local.dao.NoteDao
import com.example.noteapp.data.mapper.NoteMapper
import com.example.noteapp.data.mapper.NoteMapper.fromEntity
import com.example.noteapp.data.mapper.NoteMapper.toEntity
import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.respository.NoteRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
): NoteRepository {
    override fun getAllNotes(): LiveData<List<Note>> {
        return noteDao.getAllNotes().map { list ->
            list.map {
                fromEntity(it)
            }
        }
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(toEntity(note))
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(toEntity(note))
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(toEntity(note))
    }
}

