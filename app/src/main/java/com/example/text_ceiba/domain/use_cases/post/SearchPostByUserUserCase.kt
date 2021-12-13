package com.example.text_ceiba.domain.use_cases.post

import android.content.Context
import com.example.text_ceiba.data.repositories.post.PostRepositoryApi
import com.example.text_ceiba.domain.model.Post

class SearchPostByUserUserCase (private val context: Context) {
    suspend fun execute(userId: Int): List<Post> {
        return PostRepositoryApi().getPostsOfUser(context, userId)
    }
}