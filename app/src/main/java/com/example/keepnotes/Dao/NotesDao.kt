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

    @Insert
    suspend fun insertNotes(notes: Notes)

    @Update
    suspend fun updateNotes(notes: Notes)

    @Delete
    suspend fun deleteNotes(notes: Notes)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAllNotes()



}
