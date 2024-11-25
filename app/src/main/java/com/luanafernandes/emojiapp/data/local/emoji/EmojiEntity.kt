package com.luanafernandes.emojiapp.data.local.emoji

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emojis_table")
class EmojiEntity(
    @PrimaryKey
    val name: String,
    val url: String
)