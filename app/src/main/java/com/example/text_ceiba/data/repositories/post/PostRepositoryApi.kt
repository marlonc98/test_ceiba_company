package com.example.text_ceiba.data.repositories.post

import android.content.Context
import com.example.text_ceiba.data.repositories.common.HttpApi
import com.example.text_ceiba.data.repositories.post.api.dto.PostDto
import com.example.text_ceiba.data.repositories.post.api.dto.toPost
import com.example.text_ceiba.data.repositories.user.api.dto.UserDto
import com.example.text_ceiba.data.repositories.user.api.dto.toUser
import com.example.text_ceiba.domain.model.Post
import com.example.text_ceiba.domain.repository.IPostRepository
import com.google.gson.Gson
import okhttp3.ResponseBody

class PostRepositoryApi: IPostRepository {
    override suspend fun getPostsOfUser(context: Context, userId: Int): List<Post> {
        val response: ResponseBody = HttpApi().get("posts?userId=$userId") ?: return listOf()
        val postDto = Gson().fromJson<Array<PostDto>>(response!!.string(), Array<PostDto>::class.java)
        return postDto.map { it.toPost() }
    }
}