package com.luanafernandes.emojiapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.luanafernandes.emojiapp.data.remote.dto.Emoji

@Dao
interface EmojiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(emojis: List<EmojiEntity>)

    @Query("SELECT * FROM emojis_table")
    suspend fun getAllEmojis(): List<EmojiEntity>

}