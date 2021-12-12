package com.example.text_ceiba.data.repositories.post

import com.example.text_ceiba.domain.model.Post
import com.example.text_ceiba.domain.repository.IPostRepository

class PostRepositoryApi: IPostRepository {
    override suspend fun getPosts(): List<Post> {
        TODO("Not yet implemented")
    }

    override suspend fun getPost(id: Int): Post {
        TODO("Not yet implemented")
    }
}