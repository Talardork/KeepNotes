package com.example.keepnotes

import android.app.Application
import com.example.keepnotes.Database.NotesDatabase
import com.example.keepnotes.NotesRepository.NotesRepository

class NotesApplication : Application() {

    val database by lazy {
        NotesDatabase.getDatabase(this)
    }
    val repository by lazy {
        NotesRepository(database.myNotesDao())
    }
}