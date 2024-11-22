package com.luanafernandes.emojiapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.luanafernandes.emojiapp.data.remote.dto.Emoji

@Dao
interface EmojiDao {

    @Insert
    suspend fun insertAll(emojis: List<EmojiEntity>)
}