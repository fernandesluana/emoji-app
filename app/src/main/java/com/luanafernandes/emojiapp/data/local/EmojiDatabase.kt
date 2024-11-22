package com.luanafernandes.emojiapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EmojiEntity::class], version = 1)
abstract class EmojiDatabase : RoomDatabase() {

    abstract fun emojiDao(): EmojiDao
}