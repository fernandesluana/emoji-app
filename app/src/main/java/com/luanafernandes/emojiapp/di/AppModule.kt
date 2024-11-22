package com.luanafernandes.emojiapp.di


import com.luanafernandes.emojiapp.data.remote.EmojiApiService
import com.luanafernandes.emojiapp.data.repository.EmojiRepositoryImpl
import com.luanafernandes.emojiapp.domain.repository.EmojiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideEmojiRepository(api: EmojiApiService): EmojiRepository {
        return EmojiRepositoryImpl(api)
    }

}