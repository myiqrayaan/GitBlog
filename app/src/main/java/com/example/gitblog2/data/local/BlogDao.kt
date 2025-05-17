package com.example.gitblog2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface BlogDao {

    @Query("SELECT * FROM blogs")
    suspend fun getAllBlogs(): List<BlogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBlogs(blogs:List<BlogEntity>)


    @Query("SELECT * FROM blogs WHERE id=:id")
    suspend fun getBlogById(id:Int): BlogEntity?

    @Query("DELETE FROM blogs")
    suspend fun deleteAllBlogs()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlogContent(blogContentEntity: BlogContentEntity)

    @Query("SELECT * FROM blogContent WHERE blogId=:id")
    suspend fun getBlogContent(id:Int): BlogContentEntity
}