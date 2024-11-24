package com.luanafernandes.emojiapp.presentation.emojiListScreen


import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.luanafernandes.emojiapp.data.remote.dto.Emoji
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmojiListScreen(
    onBackClick: () -> Unit,
    emojis: List<Emoji>,
    onEmojiRemoved: (Emoji) -> Unit,
    onRefreshEmojis: () -> Unit
) {
    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val state = rememberPullToRefreshState()

val onRefresh: () -> Unit = {
    isRefreshing = true
    coroutineScope.launch {
        delay(2000)
        onRefreshEmojis()
        isRefreshing = false
    }
}

    PullToRefreshBox(
        modifier = Modifier
            .fillMaxSize(),
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        state = state
    ) {
        if (emojis.isNotEmpty()) {
            EmojiGrid(
                emojis = emojis,
                modifier = Modifier.fillMaxSize(),
                onEmojiClick = { emoji ->
                    onEmojiRemoved(emoji)
                }
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
    modifier: Modifier = Modifier,
    onEmojiClick: (Emoji) -> Unit = {}
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 60.dp),
        columns = GridCells.Fixed(4),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(emojis) { emoji ->
            EmojiItem(
                emoji = emoji,
                onClick = { onEmojiClick(emoji) })
        }
    }
}

@Composable
fun EmojiItem(
    emoji: Emoji, onClick: () -> Unit
) {
    val context = LocalContext.current
    val imageRequest = ImageRequest.Builder(context)
        .data(emoji.url)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .diskCacheKey(emoji.url)
        .crossfade(true)
        .build()

    val memoryCacheKey = MemoryCache.Key(emoji.url)

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {

                context.imageLoader.memoryCache?.remove(memoryCacheKey)
                println("Removed emoji from memory cache: ${emoji.url}")
                println(memoryCacheKey)
                onClick()
            }
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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




