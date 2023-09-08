package com.example.keepnotes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
class Notes(
    var title : String,
    var subTitle : String,
    var notes : String,
    var date : String,
    var priority : String )
{
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0

}