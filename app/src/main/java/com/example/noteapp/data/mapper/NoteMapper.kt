package com.example.noteapp.data.mapper

import com.example.noteapp.data.local.entity.NoteEntity
import com.example.noteapp.domain.model.Note

object NoteMapper {
    fun fromEntity(entity: NoteEntity) = Note(
        id = entity.id,
        title = entity.title,
        content = entity.content,
        createdAt = entity.createdAt,
        updatedAt = entity.updatedAt
    )

    fun toEntity(note: Note) = NoteEntity(
        id = note.id,
        title = note.title,
        content = note.content,
        createdAt = note.createdAt,
        updatedAt = note.updatedAt
    )
}