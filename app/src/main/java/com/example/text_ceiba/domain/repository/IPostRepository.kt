package com.example.text_ceiba.domain.repository

import com.example.text_ceiba.domain.model.Post

interface IPostRepository {
    suspend fun getPostsOfUser(userId: Int): List<Post>
}