package com.example.text_ceiba.data.repositories.user.api.dto

import kotlinx.serialization.Serializable

@Serializable
data class Company(
    val bs: String,
    val catchPhrase: String,
    val name: String
)