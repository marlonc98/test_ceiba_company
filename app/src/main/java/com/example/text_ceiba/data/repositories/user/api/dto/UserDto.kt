package com.example.text_ceiba.data.repositories.user.api.dto
import androidx.annotation.Keep
import com.example.text_ceiba.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserDto(
    val address: Address,
    val company: Company,
    val email: String,
    @SerializedName("id") val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)

fun UserDto.toUser():User{
    return User(
        id = this.id,
        name = this.name,
        username = this.username,
        email = this.email,
        phone = this.phone,
    )
}