package com.example.keepnotes.NotesRepository

import android.util.Log
import androidx.annotation.WorkerThread

import com.example.keepnotes.Dao.NotesDao
import com.example.keepnotes.model.Notes
import kotlinx.coroutines.flow.Flow

class NotesRepository(private val notesDao: NotesDao) {

    fun myAllNotes(): Flow<List<Notes>> = notesDao.getAllNotes()

    fun myHighNotes(): Flow<List<Notes>> = notesDao.getHighNotes()
    fun myMediumNotes(): Flow<List<Notes>> = notesDao.getMediumNotes()
    fun myLowNotes(): Flow<List<Notes>> = notesDao.getLowNotes()


    @WorkerThread
    suspend fun insert(notes: Notes) {
        try {
            notesDao.insertNotes(notes)
        } catch (e: Exception) {
            // Handle the exception, e.g., log or notify the user
        }
    }

    @WorkerThread
    suspend fun update(notes: Notes) {
        try {
            notesDao.updateNotes(notes)
        } catch (e: Exception) {
            // Handle the exception, e.g., log or notify the user
        }
    }

    @WorkerThread
    suspend fun delete(id : Int) {
        try {
            notesDao.deleteNotes(id)
        } catch (e: Exception) {
            // Handle the exception, e.g., log or notify the user
        }
    }

    @WorkerThread
    suspend fun deleteAll() {
        try {
            notesDao.deleteAllNotes()
        } catch (e: Exception) {
            // Handle the exception, e.g., log or notify the user
        }
    }
}
