package com.example.text_ceiba.data.repositories.user.api.dto

import kotlinx.serialization.Serializable

@Serializable
data class Geo(
    val lat: String,
    val lng: String
)