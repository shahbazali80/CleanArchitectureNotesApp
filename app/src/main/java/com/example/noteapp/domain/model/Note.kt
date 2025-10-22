package com.example.noteapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val id: Int = 0,
    val title: String,
    val content: String,
    val createdAt: Long = 0,
    val updatedAt: Long = 0
) : Parcelable