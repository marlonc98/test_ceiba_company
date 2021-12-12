package com.example.text_ceiba.domain.repository

import com.example.text_ceiba.domain.model.User

interface IUserRepoistory {
    suspend fun getUsers(): List<User>
}