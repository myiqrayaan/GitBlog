package com.example.gitblog2.data.repository

import android.util.Log
import com.example.gitblog2.core.Resource
import com.example.gitblog2.data.local.BlogContentEntity
import com.example.gitblog2.data.local.BlogDao
import com.example.gitblog2.data.mappers.toBlogEntityLists
import com.example.gitblog2.data.remote.BlogRemoteDataSource
import com.example.gitblog2.data.mappers.toBlogLists
import com.example.gitblog2.data.mappers.toBlogListsModel
import com.example.gitblog2.data.mappers.toBlogModel
import com.example.gitblog2.domain.models.BlogModel
import com.example.gitblog2.domain.repository.BlogRepository

class BlogRepositoryImpl(private val blogRemoteDataSource: BlogRemoteDataSource, private val dao: BlogDao): BlogRepository{
    override suspend fun fetchAllBlogs(): Resource<List<BlogModel>> {
        try {
            val res = blogRemoteDataSource.fetchAllBlogs()

            when (res) {
                is Resource.Failure -> {
                    val blogs = dao.getAllBlogs()
                    return if (blogs.isEmpty()) {
                        Resource.Failure("No blogs found")
                    } else {
                        return Resource.Success(blogs.toBlogListsModel())
                    }
                }

                is Resource.Success -> {
                    val data = res.data
                    dao.deleteAllBlogs()
                    dao.addBlogs(data.toBlogEntityLists())
                    return Resource.Success(data.toBlogLists())
                }

                is Resource.Loading -> return Resource.Loading

            }
        } catch (e: Exception) {
            val blogs = dao.getAllBlogs()
            return if (blogs.isEmpty()) {
                Resource.Failure("No blogs found")
            } else {
                return Resource.Success(blogs.toBlogListsModel())
            }
        }
    }

    override suspend fun getBlogById(id: Int): Resource<BlogModel> {
        try {
            val localBlog = dao.getBlogById(id)
            if (localBlog == null) {
                return Resource.Failure("No local blog found")
            }
            val content = blogRemoteDataSource.fetchBlogContent(localBlog.contentUrl)
           return when (content) {
                is Resource.Failure -> {
                   val localContent = dao.getBlogContent(id)
                    Resource.Success(localBlog.toBlogModel(content =  localContent.content))
                }

                is Resource.Loading -> return Resource.Loading
                is Resource.Success -> {
                    val con = content.data
                    val blogContentEntity = BlogContentEntity(blogId = localBlog.id, content = con)
                    dao.insertBlogContent(blogContentEntity)
                    return Resource.Success(localBlog.toBlogModel(content = con))
                }
            }
        } catch (e:Exception){
            return Resource.Failure("There is an error to fetch the blog content. kindly check your internet connection and try again.")
        }
    }
}