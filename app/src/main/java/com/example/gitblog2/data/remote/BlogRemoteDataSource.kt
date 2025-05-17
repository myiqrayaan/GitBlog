package com.example.gitblog2.data.remote

import com.example.gitblog2.core.Constants
import com.example.gitblog2.core.Resource
import com.example.gitblog2.data.remote.dto.BlogDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

interface BlogRemoteDataSource{
    suspend fun fetchAllBlogs(): Resource<List<BlogDto>>
    suspend fun fetchBlogContent(url:String): Resource<String>
}

class BlogRemoteDataSourceImpl(private val httpClient: HttpClient): BlogRemoteDataSource {


    override suspend fun fetchAllBlogs(): Resource<List<BlogDto>>{
        try {
            val res = httpClient.get(Constants.GITHUB_URL)
            return Resource.Success(res.body<List<BlogDto>>())
        }catch (e: Exception){
            return Resource.Failure(e.message.toString())
        }
    }

    override suspend fun fetchBlogContent(url: String): Resource<String> {
        try {
            val res = httpClient.get(url)
            return Resource.Success(res.bodyAsText())
        }catch (e: Exception){
            return Resource.Failure(e.message.toString())
        }
    }
}