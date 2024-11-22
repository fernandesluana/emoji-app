package com.luanafernandes.emojiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.luanafernandes.emojiapp.presentation.homeScreen.EmojiScreen
import com.luanafernandes.emojiapp.presentation.homeScreen.HomeScreenViewModel
import com.luanafernandes.emojiapp.ui.theme.EmojiAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmojiAppTheme {
                EmojiScreen(viewModel = viewModel)
            }
        }
    }
}

