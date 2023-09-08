package com.example.keepnotes.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.keepnotes.Dao.NotesDao
import com.example.keepnotes.Database.NotesDatabase
import com.example.keepnotes.NotesRepository.NotesRepository
import com.example.keepnotes.model.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {


    val repository : NotesRepository
    init {
        val dao = NotesDatabase.getDatabase(application).myNotesDao()
        repository = NotesRepository(dao)

    }
    fun addNotes(notes : Notes)= viewModelScope.launch(Dispatchers.IO) {
            repository.insert(notes)
        }

    fun updateNotes(notes : Notes)= viewModelScope.launch(Dispatchers.IO) {
            repository.update(notes)
        }
    fun deleteNotes(notes : Notes)= viewModelScope.launch(Dispatchers.IO) {
            repository.delete(notes)
        }

    fun deleteAllNotes()= viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }

    fun getAllNotes(): LiveData<List<Notes>> = repository.myAllNotes().asLiveData()



}