package com.luanafernandes.emojiapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    val login: String,
    val id: Int,
    @SerializedName("avatar_url") val avatarUrl: String
)
