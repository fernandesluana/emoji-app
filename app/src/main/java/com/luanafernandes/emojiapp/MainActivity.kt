package com.luanafernandes.emojiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.luanafernandes.emojiapp.presentation.navigation.NavGraphSetup
import com.luanafernandes.emojiapp.ui.theme.EmojiAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmojiAppTheme {
                val navController = rememberNavController()

                NavGraphSetup(
                    navController = navController
                )
            }
        }
    }
}

