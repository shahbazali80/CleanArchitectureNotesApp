package com.example.noteapp.domain.model

data class Note(
    val id: Int = 0,
    val title: String,
    val content: String,
    val createdAt: Long = 0,
    val updatedAt: Long = 0
)