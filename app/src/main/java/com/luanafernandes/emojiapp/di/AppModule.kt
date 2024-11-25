package com.luanafernandes.emojiapp.di


import android.content.Context
import androidx.room.Room
import com.luanafernandes.emojiapp.data.local.emoji.EmojiDao
import com.luanafernandes.emojiapp.data.local.EmojiDatabase
import com.luanafernandes.emojiapp.data.remote.EmojiApiService
import com.luanafernandes.emojiapp.data.repository.EmojiRepositoryImpl
import com.luanafernandes.emojiapp.domain.repository.EmojiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.github.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideEmojiApiService(retrofit: Retrofit): EmojiApiService {
        return retrofit.create(EmojiApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideEmojiRepository(
        api: EmojiApiService,
        database: EmojiDatabase
    ): EmojiRepository {
        return EmojiRepositoryImpl(api, database)
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): EmojiDatabase {
        return Room.databaseBuilder(
            context,
            EmojiDatabase::class.java,
            "emojis_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideEmojiDao(emojiDatabase: EmojiDatabase): EmojiDao {
        return emojiDatabase.emojiDao()
    }

}