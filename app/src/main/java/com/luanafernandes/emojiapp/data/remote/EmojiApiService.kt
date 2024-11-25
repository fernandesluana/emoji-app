package com.luanafernandes.emojiapp.data.remote

import com.luanafernandes.emojiapp.data.remote.dto.GitHubRepoDto
import com.luanafernandes.emojiapp.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EmojiApiService {
    @GET("emojis")
    suspend fun getEmojis(): Map<String, String>

    @GET("/users/{username}")
    suspend fun getUser(@Path("username") username: String): UserDto

    @GET("/users/{username}/repos")
    suspend fun getUserRepos(
        @Path("username") username: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): List<GitHubRepoDto>


}