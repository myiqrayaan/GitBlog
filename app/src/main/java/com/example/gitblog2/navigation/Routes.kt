package com.example.gitblog2.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object BlogList: Routes()

    @Serializable
    data class BlogDetail(val id:Int): Routes()
}