package com.luanafernandes.emojiapp.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.luanafernandes.emojiapp.data.local.EmojiDatabase
import com.luanafernandes.emojiapp.data.mapper.dtoListToEmojiList
import com.luanafernandes.emojiapp.data.mapper.emojiEntityListToEmojiList
import com.luanafernandes.emojiapp.data.mapper.emojiDtoListToEmojiEntityList
import com.luanafernandes.emojiapp.data.mapper.mapToEmojiList
import com.luanafernandes.emojiapp.data.mapper.userDtoToUserEntity
import com.luanafernandes.emojiapp.data.mapper.userEntityToUser
import com.luanafernandes.emojiapp.data.paging.RepoPagingSource
import com.luanafernandes.emojiapp.data.remote.EmojiApiService
import com.luanafernandes.emojiapp.domain.model.Emoji
import com.luanafernandes.emojiapp.domain.model.GitHubRepo
import com.luanafernandes.emojiapp.domain.model.User
import com.luanafernandes.emojiapp.domain.repository.EmojiRepository
import kotlinx.coroutines.flow.Flow


private const val USERNAME = "google"

class EmojiRepositoryImpl(
    private val api: EmojiApiService,
    private val database: EmojiDatabase
): EmojiRepository {

    private val emojiDao = database.emojiDao()
    private val userDao = database.userDao()

  override suspend fun getAllEmojis(): List<Emoji> {
      return try {

          val cachedEmojis = emojiDao.getAllEmojis()

          if(cachedEmojis.isNotEmpty()) {
              val cachedEmojiList = emojiEntityListToEmojiList(cachedEmojis)
              return cachedEmojiList
          }
          val response = api.getEmojis()
          val emojiDtoList = mapToEmojiList(response)

          emojiDao.insertAll(emojiDtoListToEmojiEntityList(emojiDtoList))
          val emojis = dtoListToEmojiList(emojiDtoList)
          emojis
      } catch (e: Exception) {
          e.printStackTrace()
          emptyList()
      }
  }

    override suspend fun getUser(username: String): User? {
        return try {
            val cachedUser = userDao.getUserByLogin(username)

            if (cachedUser != null) {
            return userEntityToUser(cachedUser)
        }
            val userDto = api.getUser(username)
            val userEntity = userDtoToUserEntity(userDto)

            userDao.insertUser(userEntity)
            userEntityToUser(userEntity)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getAllUsers(): List<User> {
        val cachedUsers = userDao.getAllUsers()

        return cachedUsers.map { userEntity ->
            userEntityToUser(userEntity)
        }
    }

    override suspend fun deleteUser(username: String) {
        try {
            val user = userDao.getUserByLogin(username)
            if (user != null) {
                userDao.deleteUser(username)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getUserRepos(): Flow<PagingData<GitHubRepo>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                RepoPagingSource(api, USERNAME)
            }
        ).flow
    }


}