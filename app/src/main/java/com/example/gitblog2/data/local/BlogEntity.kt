package com.example.gitblog2.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blogs")
data class BlogEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val thumbnailUrl: String,
    val contentUrl: String,
    )


@Entity(tableName = "blogContent")
data class BlogContentEntity(
    @PrimaryKey
    val blogId:Int,
    val content: String
)