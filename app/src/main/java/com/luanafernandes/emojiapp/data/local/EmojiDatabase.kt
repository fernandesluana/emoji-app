package com.luanafernandes.emojiapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EmojiEntity::class, UserEntity::class], version = 1)
abstract class EmojiDatabase : RoomDatabase() {

    abstract fun emojiDao(): EmojiDao
    abstract fun userDao(): UserDao
}