package com.example.keepnotes.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope

import com.example.keepnotes.Database.NotesDatabase
import com.example.keepnotes.NotesRepository.NotesRepository
import com.example.keepnotes.model.Notes
import kotlinx.coroutines.Dispatchers

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
    fun deleteNotes(id : Int)= viewModelScope.launch(Dispatchers.IO) {
            repository.delete(id)
        }

    fun deleteAllNotes()= viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }

    fun getAllNotes(): LiveData<List<Notes>> = repository.myAllNotes().asLiveData()

    fun getHighNotes(): LiveData<List<Notes>> = repository.myHighNotes().asLiveData()
    fun getMediumNotes(): LiveData<List<Notes>> = repository.myMediumNotes().asLiveData()
    fun getLowNotes(): LiveData<List<Notes>> = repository.myLowNotes().asLiveData()


}