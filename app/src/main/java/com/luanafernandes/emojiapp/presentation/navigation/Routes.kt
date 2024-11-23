package com.luanafernandes.emojiapp.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    data object HomeScreen : Routes()
    @Serializable
    data object EmojiListScreen : Routes()
    @Serializable
    data object AvatarListScreen : Routes()
    @Serializable
    data object GoogleReposScreen : Routes()
}