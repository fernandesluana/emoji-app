package com.luanafernandes.emojiapp.domain.repository

import com.luanafernandes.emojiapp.data.remote.dto.EmojiDto
import com.luanafernandes.emojiapp.domain.model.Emoji
import com.luanafernandes.emojiapp.domain.model.User

interface EmojiRepository {

    suspend fun getAllEmojis(): List<Emoji>

    suspend fun getUser(username: String): User?
}