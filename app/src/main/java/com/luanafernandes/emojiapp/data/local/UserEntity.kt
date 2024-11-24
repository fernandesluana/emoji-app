package com.luanafernandes.emojiapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class UserEntity(
    @PrimaryKey val login: String,
    val id: Int,
    val avatarUrl: String
)
