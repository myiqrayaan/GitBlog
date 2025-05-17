package com.example.gitblog2.domain.models

data class BlogModel(
    val id: Int,
    val title: String,
    val thumbnailUrl: String,
    val contentUrl: String,
    val content: String?
)

val blogs = listOf<BlogModel>(
    BlogModel(id = 1, title = "First blog", thumbnailUrl = "https://cdn.pixabay.com/photo/2015/08/03/13/58/whatsapp-873316_1280.png", content = "", contentUrl = ""),
    BlogModel(id = 2, title = "Second blogSecond blogSecond blogSecond blog", thumbnailUrl = "https://cdn.pixabay.com/photo/2015/08/03/13/58/whatsapp-873316_1280.png", content = "", contentUrl = ""),
    BlogModel(id = 3, title = "Third blog, Third blog, Third blog, Third blog, Third blog, Third blog, Third blog, ", thumbnailUrl = "https://cdn.pixabay.com/photo/2015/08/03/13/58/whatsapp-873316_1280.png", content = "", contentUrl = ""),
    BlogModel(id = 4, title = "Fourth blog", thumbnailUrl = "https://cdn.pixabay.com/photo/2015/08/03/13/58/whatsapp-873316_1280.png", content = "", contentUrl = ""),
    BlogModel(id = 5, title = "Fifth blog", thumbnailUrl = "https://cdn.pixabay.com/photo/2015/08/03/13/58/whatsapp-873316_1280.png", content = "", contentUrl = ""),
)