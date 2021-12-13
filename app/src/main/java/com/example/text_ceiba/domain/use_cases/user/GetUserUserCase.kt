package com.example.text_ceiba.domain.use_cases.user

import android.content.Context
import com.example.text_ceiba.data.repositories.user.UserRepositoryLocal
import com.example.text_ceiba.domain.model.User

class GetUserUserCase (private val context: Context){
    suspend fun execute(userId: Int) : User?{
        return UserRepositoryLocal().getUser(context, userId)
    }
}