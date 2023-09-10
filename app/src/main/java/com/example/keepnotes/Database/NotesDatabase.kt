package com.example.keepnotes.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

import com.example.keepnotes.Dao.NotesDao
import com.example.keepnotes.model.Notes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase(){
    //database must be an abstract class

    //here we will create an abstract function from the noteDAO interface.
    abstract fun myNotesDao():NotesDao

    companion object{

        @Volatile
        private var INSTANCE : NotesDatabase? = null
        //volatile means make this instance will be visible to all the threads.
        //now lets create the function that will create the instance of this class


        fun getDatabase(context: Context) : NotesDatabase{
            return INSTANCE?: synchronized(this){
                //if instance is null, it will run the synchronized block.
                //it allows the creation of only one instance at a time.

                val instance = Room.databaseBuilder(context.applicationContext,
                    NotesDatabase::class.java, "notes_database").build()
                INSTANCE = instance
                instance

            }

        }

    }
//    private class NotesDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback(){
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            INSTANCE ?. let { database ->
//                //when instance is not null, this code will work.
//                //database.myNotesDao().insertNotes("t")
//
//                scope.launch {
//                    val notesDao = database.myNotesDao()
//
//                    notesDao.insertNotes(Notes("Title","subtitle","hasnksan","6 sept 2023",
//                        "3"))
//
//                }
//
//            }
//        }
//    }
}