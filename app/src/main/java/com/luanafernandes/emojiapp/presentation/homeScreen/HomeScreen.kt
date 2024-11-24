package com.luanafernandes.emojiapp.presentation.homeScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luanafernandes.emojiapp.data.remote.dto.EmojiDto
import com.luanafernandes.emojiapp.domain.model.Emoji
import com.luanafernandes.emojiapp.domain.model.User
import com.luanafernandes.emojiapp.presentation.components.ImageCard

@Composable
fun HomeScreen(
    emojis: List<Emoji>,
    onEmojiListClick: () -> Unit,
    onSearchClick: (String) -> Unit,
    user: User?
) {
    var randomEmoji by remember { mutableStateOf<Emoji?>(null) }
    var username by remember { mutableStateOf("") }
    var userAvatarUrl by remember { mutableStateOf<String?>(null) }

    user?.avatarUrl?.let {
        userAvatarUrl = it
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
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
            modifier = Modifier.padding(20.dp)
        ) {
            Text(text = "Random Emoji")
        }


        Button(
            onClick = { onEmojiListClick() },
            modifier = Modifier.padding(20.dp)
        ) {
            Text(text = "Emoji List")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text(text = "Search by username...") }
            )
            IconButton(
                onClick = {
                    randomEmoji = null
                    onSearchClick(username)
                },
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }
        }
    }
}



