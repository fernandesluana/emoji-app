package com.luanafernandes.emojiapp.domain.repository

import androidx.paging.PagingData
import com.luanafernandes.emojiapp.domain.model.Emoji
import com.luanafernandes.emojiapp.domain.model.GitHubRepo
import com.luanafernandes.emojiapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface EmojiRepository {

    suspend fun getAllEmojis(): List<Emoji>

    suspend fun getUser(username: String): User?

    suspend fun getAllUsers(): List<User>

    suspend fun deleteUser(username: String)

    fun getUserRepos(): Flow<PagingData<GitHubRepo>>
}