package com.luanafernandes.emojiapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.luanafernandes.emojiapp.data.local.emoji.EmojiDao
import com.luanafernandes.emojiapp.data.local.emoji.EmojiEntity

import com.luanafernandes.emojiapp.data.local.user.UserDao
import com.luanafernandes.emojiapp.data.local.user.UserEntity

@Database(entities = [EmojiEntity::class, UserEntity::class], version = 1)
abstract class EmojiDatabase : RoomDatabase() {

    abstract fun emojiDao(): EmojiDao
    abstract fun userDao(): UserDao


}