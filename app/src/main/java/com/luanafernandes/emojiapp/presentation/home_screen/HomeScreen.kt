package com.luanafernandes.emojiapp.presentation.home_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.luanafernandes.emojiapp.domain.model.Emoji
import com.luanafernandes.emojiapp.domain.model.User
import com.luanafernandes.emojiapp.presentation.components.ImageCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    emojis: List<Emoji>,
    onEmojiListClick: () -> Unit,
    onSearchClick: (String) -> Unit,
    user: User?,
    onAvatarListClick: () -> Unit,
    onGoogleReposClick: () -> Unit
) {
    var randomEmoji by remember { mutableStateOf<Emoji?>(null) }
    var username by remember { mutableStateOf("") }
    var userAvatarUrl by remember { mutableStateOf<String?>(null) }

    user?.avatarUrl?.let {
        userAvatarUrl = it
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Emoji App") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
            .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            AnimatedVisibility(visible = randomEmoji != null || userAvatarUrl != null) {
                val imageUrl = when {
                    randomEmoji != null -> randomEmoji?.url
                    userAvatarUrl != null -> userAvatarUrl
                    else -> ""
                }

                ImageCard(
                    imageUrl = imageUrl ?: "",
                    modifier = Modifier.padding(8.dp)
                )
            }

            Button(
                onClick = {
                    randomEmoji = emojis.random()
                    userAvatarUrl = null
                },
                modifier = Modifier
                    .width(150.dp)
                    .padding(5.dp),
                shape = RoundedCornerShape(8.dp),

            ) {
                Text(text = "Random Emoji")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .weight(1f),
                    placeholder = { Text(text = "Search by username...") },
                    shape = RoundedCornerShape(8.dp)
                )
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            randomEmoji = null
                            onSearchClick(username)
                            username = ""
                                   },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = { onEmojiListClick() },
                    modifier = Modifier
                        .width(150.dp).padding(5.dp),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(text = "Emoji List")
                }

                Button(
                    onClick = { onAvatarListClick() },
                    modifier = Modifier
                        .width(150.dp)
                        .padding(5.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Avatar List")
                }

                Button(
                    onClick = { onGoogleReposClick() },
                    modifier = Modifier
                        .width(150.dp)
                        .padding(5.dp),
                    shape = RoundedCornerShape(8.dp),

                ) {
                    Text(text = "Google Repos")
                }

        }
    }
}



