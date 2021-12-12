package com.example.text_ceiba.data.repositories.user

import com.example.text_ceiba.domain.model.User
import com.example.text_ceiba.domain.repository.IUserRepoistory

class UserRepositoryApi : IUserRepoistory {
    override suspend fun getUsers(): List<User> {
        TODO("Not yet implemented")
    }
}