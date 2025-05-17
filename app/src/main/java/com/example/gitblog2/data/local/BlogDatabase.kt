package com.example.gitblog2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [BlogEntity::class, BlogContentEntity::class], exportSchema = false)
abstract class BlogDatabase: RoomDatabase() {

    abstract fun dao(): BlogDao
}