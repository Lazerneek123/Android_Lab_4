package com.example.socialnetworkteo.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_information")
data class User(
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "post")
    val post: String = "",
    @ColumnInfo(name = "age")
    val age: Int = 0,
    @ColumnInfo(name = "online")
    val online: String = "",
    @ColumnInfo(name = "email")
    val email: String = "",
    @ColumnInfo(name = "photo")
    val photo: Int = 0,
    @ColumnInfo(name = "hobby")
    val hobby: String = "",
    @ColumnInfo(name = "description")
    val description: String = ""
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

