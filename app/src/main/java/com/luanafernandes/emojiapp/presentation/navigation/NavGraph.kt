package com.luanafernandes.emojiapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.luanafernandes.emojiapp.presentation.AvatarListScreen
import com.luanafernandes.emojiapp.presentation.emojiListScreen.EmojiListScreen
import com.luanafernandes.emojiapp.presentation.homeScreen.HomeScreen
import com.luanafernandes.emojiapp.presentation.homeScreen.HomeScreenViewModel

@Composable
fun NavGraphSetup(
    navController: NavHostController
) {
    val homeViewModel: HomeScreenViewModel = hiltViewModel()
    val emojis by homeViewModel.emojis.collectAsState()
    val user by homeViewModel.user.collectAsState()
    val users by homeViewModel.users.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Routes.HomeScreen
    ) {
        composable<Routes.HomeScreen> {
            HomeScreen(
                emojis = emojis,
                onEmojiListClick = {
                    navController.navigate(Routes.EmojiListScreen)
                },
                onSearchClick = {username ->
                    homeViewModel.fetchUser(username = username)
                },
                user = user,
                onAvatarListClick = {
                    navController.navigate(Routes.AvatarListScreen)
                }

            )
        }
        composable<Routes.EmojiListScreen> {
            EmojiListScreen(
                onBackClick = {
                    navController.navigateUp()
                },
                emojis = emojis,
                onEmojiRemoved = { emoji ->
                    homeViewModel.removeEmojiFromList(emoji)
                },
                onRefreshEmojis = {
                    homeViewModel.fetchEmojis()
                }
            )
        }
        composable<Routes.AvatarListScreen> {
            AvatarListScreen(
                users = users,
                onBackClick = {}
            )
        }
    }
}









