package com.luanafernandes.emojiapp.presentation.homeScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.luanafernandes.emojiapp.data.remote.dto.Emoji

@Composable
fun EmojiScreen(viewModel: HomeScreenViewModel) {
    val emojis by viewModel.emojis.collectAsState()
    var randomEmoji by remember { mutableStateOf<Emoji?>(null) }

    LaunchedEffect(Unit) {
        viewModel.fetchEmojis()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        AnimatedVisibility(visible = randomEmoji != null) {

            randomEmoji?.let { emoji ->
                val imageRequest = ImageRequest.Builder(LocalContext.current)
                    .data(emoji.url)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .crossfade(true)
                    .build()

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        model = imageRequest,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }
    }
        Button(
            onClick = {
                    randomEmoji = emojis.random()
                    println("Random Emoji Selected: $randomEmoji")
            },
            modifier = Modifier.padding(20.dp)
        ) {
            Text(text = "Get Emojis")
        }

    }
}


