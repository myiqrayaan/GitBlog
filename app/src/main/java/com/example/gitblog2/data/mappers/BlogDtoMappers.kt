package com.example.gitblog2.data.mappers

import com.example.gitblog2.data.local.BlogEntity
import com.example.gitblog2.data.remote.dto.BlogDto
import com.example.gitblog2.domain.models.BlogModel

fun BlogDto.toBlogModel() = BlogModel(
    id = id,
    title = title,
    content = null,
    contentUrl = contentUrl,
    thumbnailUrl = thumbnailUrl
)

fun List<BlogDto>.toBlogLists() = map { it.toBlogModel() }


fun BlogDto.toBlogEntity() = BlogEntity(
    id = id,
    title = title,
    contentUrl = contentUrl,
    thumbnailUrl = thumbnailUrl
)

fun List<BlogDto>.toBlogEntityLists() = map { it.toBlogEntity() }

fun BlogEntity.toBlogModel(content:String? = null) = BlogModel(
    id = id,
    title = title,
    content = content,
    contentUrl = contentUrl,
    thumbnailUrl = thumbnailUrl
)

fun List<BlogEntity>.toBlogListsModel() = map { it.toBlogModel() }
