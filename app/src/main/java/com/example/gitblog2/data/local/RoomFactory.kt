package com.example.gitblog2.data.local

import android.content.Context
import androidx.room.Room

object RoomFactory {
    fun create(context: Context): BlogDatabase {
       return Room.databaseBuilder(context, BlogDatabase::class.java, "room_db1")
           .fallbackToDestructiveMigration(true)
           .build()
    }
}