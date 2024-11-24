package com.luanafernandes.emojiapp.data.remote

import com.luanafernandes.emojiapp.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface EmojiApiService {
    @GET("emojis")
    suspend fun getEmojis(): Map<String, String>

    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): UserDto

}