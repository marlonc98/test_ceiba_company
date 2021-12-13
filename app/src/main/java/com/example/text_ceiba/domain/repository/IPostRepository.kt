package com.example.text_ceiba.domain.repository

import android.content.Context
import com.example.text_ceiba.domain.model.Post

interface IPostRepository {
    suspend fun getPostsOfUser(context: Context, userId: Int): List<Post>
}