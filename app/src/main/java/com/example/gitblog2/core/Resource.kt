package com.example.gitblog2.core

sealed class Resource<out T> {
    data object Loading: Resource<Nothing>()
    data class Success<T>(val data:T): Resource<T>()
    data class Failure(val error:String): Resource<Nothing>()
}