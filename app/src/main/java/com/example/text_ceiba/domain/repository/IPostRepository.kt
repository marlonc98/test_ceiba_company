package com.example.text_ceiba.domain.repository

import com.example.text_ceiba.domain.model.Post

interface IPostRepository {
    suspend fun getPosts(): List<Post>
    suspend fun getPost(id: Int): Post
}