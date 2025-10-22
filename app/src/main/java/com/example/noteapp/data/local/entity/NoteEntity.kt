package com.example.noteapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteapp.utils.Constants

@Entity(Constants.NOTE_TABLE_NAME)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val createdAt: Long,
    val updatedAt: Long
)