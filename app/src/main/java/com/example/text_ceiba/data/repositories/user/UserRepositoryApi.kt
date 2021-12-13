package com.example.text_ceiba.data.repositories.user

import android.content.Context
import android.util.Log
import com.example.text_ceiba.data.repositories.common.HttpApi
import com.example.text_ceiba.data.repositories.user.api.dto.UserDto
import com.example.text_ceiba.data.repositories.user.api.dto.toUser
import com.example.text_ceiba.domain.model.User
import com.example.text_ceiba.domain.repository.IUserRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.json.JSONArray

class UserRepositoryApi : IUserRepository {
    public override suspend fun getUsers(context: Context): List<User> {
        val response: ResponseBody = HttpApi().get("users") ?: return listOf()
        val usersDto = Gson().fromJson<Array<UserDto>>(response!!.string(), Array<UserDto>::class.java)
        return usersDto.map { it.toUser() }
    }

    override suspend fun getUser(context: Context, userId: Int): User? {
        return null
    }

    override suspend fun saveUser(context: Context, user: User): Boolean {
        return true
    }

    override suspend fun saveMultipleUser(context: Context, users: List<User>): Boolean {
        return true
    }
}