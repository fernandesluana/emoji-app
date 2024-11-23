package com.luanafernandes.emojiapp.presentation.emojiListScreen


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.luanafernandes.emojiapp.data.remote.dto.Emoji

@Composable
fun EmojiListScreen(
    onBackClick: () -> Unit,
    emojis: List<Emoji>
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        IconButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 20.dp),
            onClick = onBackClick
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
        if (emojis.isNotEmpty()) {
            EmojiGrid(
                emojis = emojis,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Text(
                text = "No emojis available",
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun EmojiGrid(
    emojis: List<Emoji>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 60.dp),
        columns = GridCells.Fixed(4),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(emojis) { emoji ->
            EmojiItem(emoji = emoji)
        }
    }
}

@Composable
fun EmojiItem(emoji: Emoji) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageRequest = ImageRequest.Builder(LocalContext.current)
                .data(emoji.url)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .crossfade(true)
                .build()

            AsyncImage(
                model = imageRequest,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    }
}

