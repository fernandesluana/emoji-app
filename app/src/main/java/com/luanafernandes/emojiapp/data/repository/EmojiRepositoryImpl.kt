package com.luanafernandes.emojiapp.data.repository


import com.luanafernandes.emojiapp.data.local.EmojiDatabase
import com.luanafernandes.emojiapp.data.mapper.entityToEmojiList
import com.luanafernandes.emojiapp.data.mapper.mapToEmojiEntityList
import com.luanafernandes.emojiapp.data.mapper.mapToEmojiList
import com.luanafernandes.emojiapp.data.remote.EmojiApiService
import com.luanafernandes.emojiapp.data.remote.dto.Emoji
import com.luanafernandes.emojiapp.domain.repository.EmojiRepository


class EmojiRepositoryImpl(
    private val api: EmojiApiService,
    database: EmojiDatabase
): EmojiRepository {

    private val dao = database.emojiDao()

  override suspend fun getAllEmojis(): List<Emoji> {
      return try {

          val cachedEmojis = dao.getAllEmojis()

          if(cachedEmojis.isNotEmpty()) {
              return entityToEmojiList(cachedEmojis)
          }

          val response = api.getEmojis()
          val emojiList = mapToEmojiList(response)

          dao.insertAll(mapToEmojiEntityList(emojiList))
          emojiList
      } catch (e: Exception) {
          e.printStackTrace()
          emptyList()
      }
  }
}