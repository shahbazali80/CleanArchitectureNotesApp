package com.example.noteapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.data.local.dao.NoteDao
import com.example.noteapp.data.local.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 2, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}