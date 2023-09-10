package com.example.keepnotes.Dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.keepnotes.model.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    fun getAllNotes(): Flow<List<Notes>>

    //filtering feature below
    @Query("SELECT * FROM notes_table WHERE priority = 1")
    fun getHighNotes(): Flow<List<Notes>>
    @Query("SELECT * FROM notes_table WHERE priority = 2")
    fun getMediumNotes(): Flow<List<Notes>>
    @Query("SELECT * FROM notes_table WHERE priority = 3")
    fun getLowNotes(): Flow<List<Notes>>
    //filtering feature above

    @Insert
    suspend fun insertNotes(notes: Notes)

    @Update
    suspend fun updateNotes(notes: Notes)

    @Query("DELETE FROM notes_table WHERE id =:id")
    suspend fun deleteNotes(id : Int)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAllNotes()



}
