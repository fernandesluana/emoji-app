package com.luanafernandes.emojiapp.domain.repository

import com.luanafernandes.emojiapp.domain.model.Emoji
import com.luanafernandes.emojiapp.domain.model.User

interface EmojiRepository {

    suspend fun getAllEmojis(): List<Emoji>

    suspend fun getUser(username: String): User?

    suspend fun getAllUsers(): List<User>

    suspend fun deleteUser(username: String)
}