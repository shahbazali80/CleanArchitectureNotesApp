package com.example.noteapp.di

import android.content.Context
import androidx.room.Room
import com.example.noteapp.data.local.dao.NoteDao
import com.example.noteapp.data.local.database.NotesDatabase
import com.example.noteapp.data.repository.NoteRepositoryImpl
import com.example.noteapp.domain.respository.NoteRepository
import com.example.noteapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : NotesDatabase =
        Room.databaseBuilder(
            context,
            NotesDatabase::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideEntityDao(db: NotesDatabase): NoteDao = db.noteDao()

    @Provides
    @Singleton
    fun provideNoteRepository(
        dao: NoteDao
    ): NoteRepository = NoteRepositoryImpl(dao)
}