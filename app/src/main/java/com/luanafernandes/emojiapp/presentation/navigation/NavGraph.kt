package com.luanafernandes.emojiapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.luanafernandes.emojiapp.presentation.avatar_list_screen.AvatarListScreen
import com.luanafernandes.emojiapp.presentation.repo_list_screen.RepoListScreen
import com.luanafernandes.emojiapp.presentation.emoji_list_screen.EmojiListScreen
import com.luanafernandes.emojiapp.presentation.home_screen.HomeScreen
import com.luanafernandes.emojiapp.presentation.AppViewModel

@Composable
fun NavGraphSetup(
    navController: NavHostController
) {
    val homeViewModel: AppViewModel = hiltViewModel()
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
                onSearchClick = { username ->
                    homeViewModel.fetchUser(username = username)
                },
                user = user,
                onAvatarListClick = {
                    navController.navigate(Routes.AvatarListScreen)
                },
                onGoogleReposClick = {
                    navController.navigate(Routes.RepoListScreen)
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

            LaunchedEffect(Unit) {
                homeViewModel.fetchUsers()
            }

            AvatarListScreen(
                onBackClick = {navController.navigateUp()},
                users = users,
                onDeleteUser = { username ->
                    homeViewModel.deleteUser(username)
                }
            )
        }
        composable<Routes.RepoListScreen> {
            RepoListScreen(
                repos = homeViewModel.repos.collectAsLazyPagingItems(),
                onBackClick = {
                    navController.navigateUp()
                }
            )


        }
    }
}









