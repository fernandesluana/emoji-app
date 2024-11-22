package com.luanafernandes.emojiapp.data.remote

import retrofit2.http.GET

interface EmojiApiService {
    @GET("emojis")
    suspend fun getEmojis(): Map<String, String>
}