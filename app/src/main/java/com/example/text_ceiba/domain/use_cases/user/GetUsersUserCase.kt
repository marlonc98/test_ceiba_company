package com.example.text_ceiba.domain.use_cases.user

import android.content.Context
import com.example.text_ceiba.data.repositories.user.UserRepositoryApi
import com.example.text_ceiba.data.repositories.user.UserRepositoryLocal
import com.example.text_ceiba.domain.model.User

class GetUsersUserCase (private val context: Context) {
    suspend fun execute() : List<User> {
        //check if local have data
        var users = UserRepositoryLocal().getUsers(context)
        if(users.isNotEmpty()) return users
        //if not, get data from remote
        users = UserRepositoryApi().getUsers(context)
        //save data in local
        UserRepositoryLocal().saveMultipleUser(context, users)
        return users
    }
}