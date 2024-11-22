package com.luanafernandes.emojiapp.data.repository

import com.luanafernandes.emojiapp.data.mapper.mapToEmojiList
import com.luanafernandes.emojiapp.data.remote.EmojiApiService
import com.luanafernandes.emojiapp.data.remote.dto.Emoji
import com.luanafernandes.emojiapp.domain.repository.EmojiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EmojiRepositoryImpl(
    private val api: EmojiApiService
): EmojiRepository {

  override suspend fun getAllEmojis(): List<Emoji> {
      val response = api.getEmojis()
      return mapToEmojiList(response)
    }
}