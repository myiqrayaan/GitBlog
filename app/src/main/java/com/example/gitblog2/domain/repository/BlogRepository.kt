package com.example.gitblog2.domain.repository

import com.example.gitblog2.core.Resource
import com.example.gitblog2.domain.models.BlogModel

interface BlogRepository {
    suspend fun fetchAllBlogs(): Resource<List<BlogModel>>
    suspend fun getBlogById(id: Int): Resource<BlogModel>
}