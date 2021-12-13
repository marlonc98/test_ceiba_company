package com.example.text_ceiba.data.repositories.user

import android.content.Context
import com.example.text_ceiba.data.repositories.common.local.tables.UserSqlite
import com.example.text_ceiba.domain.model.User
import com.example.text_ceiba.domain.repository.IUserRepository
import com.google.gson.Gson
import org.json.JSONObject

class UserRepositoryLocal: IUserRepository {
    override suspend fun getUsers(context: Context): List<User> {
        val response: String =  UserSqlite(context).get() ?: return listOf()
        val users = Gson().fromJson<Array<User>>(response, Array<User>::class.java)
        return users!!.toList()

    }

    override suspend fun saveUser(context: Context, user: User): Boolean {
        val stringObject: String = Gson().toJson(user);
        val jsonObject = JSONObject(stringObject)
        return UserSqlite(context).insert(jsonObject)
    }

    override suspend fun saveMultipleUser(context: Context, users: List<User>): Boolean {
        try {
            users.forEach() {
                saveUser(context, it)
            }
            return true
        }catch (e: Exception){
            return false
        }
    }
}