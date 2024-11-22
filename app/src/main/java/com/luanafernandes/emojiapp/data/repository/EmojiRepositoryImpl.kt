package com.luanafernandes.emojiapp.data.repository


import com.luanafernandes.emojiapp.data.local.EmojiDatabase
import com.luanafernandes.emojiapp.data.mapper.mapToEmojiEntityList
import com.luanafernandes.emojiapp.data.mapper.mapToEmojiList
import com.luanafernandes.emojiapp.data.remote.EmojiApiService
import com.luanafernandes.emojiapp.data.remote.dto.Emoji
import com.luanafernandes.emojiapp.domain.repository.EmojiRepository


class EmojiRepositoryImpl(
    private val api: EmojiApiService,
    private val database: EmojiDatabase
): EmojiRepository {

    private val dao = database.emojiDao()

  override suspend fun getAllEmojis(): List<Emoji> {
      val response = api.getEmojis()
      val emojiList = mapToEmojiList(response)
      dao.insertAll(mapToEmojiEntityList(emojiList))
      return emojiList
    }
}