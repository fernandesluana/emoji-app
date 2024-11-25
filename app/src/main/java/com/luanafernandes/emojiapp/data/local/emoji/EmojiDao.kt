package com.luanafernandes.emojiapp.data.local.emoji

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmojiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(emojis: List<EmojiEntity>)

    @Query("SELECT * FROM emojis_table")
    suspend fun getAllEmojis(): List<EmojiEntity>

}