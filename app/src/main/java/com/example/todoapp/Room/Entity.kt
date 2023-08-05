package com.example.todoapp.Room

import androidx.room.PrimaryKey

@androidx.room.Entity(tableName = "MyTable")
data class Entity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val task:String
)
