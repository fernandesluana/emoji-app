package com.luanafernandes.emojiapp.domain.repository

import com.luanafernandes.emojiapp.data.remote.dto.Emoji

interface EmojiRepository {

    suspend fun getAllEmojis(): List<Emoji>
}