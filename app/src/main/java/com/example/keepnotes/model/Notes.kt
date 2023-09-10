package com.example.keepnotes.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes_table")
class Notes(

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,

    var title : String,
    var subTitle : String,
    var notes : String,
    var date : String,
    var priority : String ) : Parcelable
{



}