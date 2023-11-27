package com.example.roombook

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "book_table")
class Book(@PrimaryKey
           @ColumnInfo(name = "title")
           val title:String,
           @ColumnInfo(name = "author")
           val author:String)  : Serializable {

}