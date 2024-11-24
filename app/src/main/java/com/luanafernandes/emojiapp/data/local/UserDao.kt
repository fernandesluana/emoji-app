package com.luanafernandes.emojiapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM users_table WHERE login = :username")
    suspend fun getUserByLogin(username: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM users_table")
    suspend fun getAllUsers(): List<UserEntity>
}