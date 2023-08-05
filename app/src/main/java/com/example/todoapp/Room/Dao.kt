package com.example.todoapp.Room

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@androidx.room.Dao
interface Dao {

     @Insert
     suspend fun insert(entity: Entity)

     @Delete
     suspend fun delete(entity: Entity)

     @Query("Select * from MyTable")
     fun fetchData():LiveData<List<Entity>>
}