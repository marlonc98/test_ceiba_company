package com.example.text_ceiba.data.repositories.post.api.dto

import com.example.text_ceiba.domain.model.Post

data class PostDto(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)

fun PostDto.toPost():Post{
    return Post(
        body = this.body,
        id = this.id,
        title = this.title,
        userId = this.userId
    )
}