package com.luanafernandes.emojiapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.luanafernandes.emojiapp.presentation.emojiListScreen.EmojiListScreen
import com.luanafernandes.emojiapp.presentation.homeScreen.HomeScreen
import com.luanafernandes.emojiapp.presentation.homeScreen.HomeScreenViewModel

@Composable
fun NavGraphSetup(
    navController: NavHostController
) {
    val homeViewModel : HomeScreenViewModel = hiltViewModel()
    val emojis by homeViewModel.emojis.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Routes.HomeScreen
    ) {
        composable<Routes.HomeScreen> {



            HomeScreen(
                onEmojiListClick = {
                    navController.navigate(Routes.EmojiListScreen)
                },
                emojis = emojis
            )
        }
        composable<Routes.EmojiListScreen> {
            EmojiListScreen(
                onBackClick = {
                    navController.navigateUp()
                },
                emojis = emojis
            )
        }

    }
}