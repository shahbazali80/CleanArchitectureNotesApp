package com.example.noteapp.data.repository

import com.example.noteapp.data.local.dao.NoteDao
import com.example.noteapp.data.mapper.NoteMapper.fromEntity
import com.example.noteapp.data.mapper.NoteMapper.toEntity
import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.respository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
): NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes().map { list ->
            list.map {
                fromEntity(it)
            }
        }
    }

    override suspend fun insertNote(note: Note): Long {
        return noteDao.insertNote(toEntity(note))
    }

    override suspend fun updateNote(note: Note): Int {
        return noteDao.updateNote(toEntity(note))
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(toEntity(note))
    }
}

