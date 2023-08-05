package com.example.todoapp

import com.example.todoapp.Room.Dao
import com.example.todoapp.Room.Entity

interface OnIItemClickListen {
    fun onDeleteclick(entity: Entity)
}