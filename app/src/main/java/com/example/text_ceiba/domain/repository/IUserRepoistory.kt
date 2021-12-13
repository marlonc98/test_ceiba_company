package com.example.text_ceiba.domain.repository

import android.content.Context
import com.example.text_ceiba.domain.model.User

interface IUserRepository {
    suspend fun getUsers(context: Context): List<User>
    suspend fun getUser(context: Context, userId: Int): User?
    suspend fun saveUser(context: Context, user: User): Boolean
    suspend fun saveMultipleUser(context: Context, users: List<User>): Boolean
}