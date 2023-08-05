package com.example.todoapp.Room

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entity::class], version = 1)
abstract class MyDatabase():RoomDatabase() {
  abstract fun CallDao():Dao

    companion object
    {
        @Volatile
        var instance:MyDatabase?=null
        fun CreateDatabase(context: Context):MyDatabase
        {

            if(instance==null)
            {
                synchronized(this)
                {
                    instance = Room.databaseBuilder(context, MyDatabase::class.java, "MyDB").build()
                    return instance!!
                }
            }
            return instance!!
        }
    }
}