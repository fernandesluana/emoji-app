package com.luanafernandes.emojiapp.presentation.repo_list_screen


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.luanafernandes.emojiapp.domain.model.GitHubRepo


@Composable
fun RepoListScreen(
    repos: LazyPagingItems<GitHubRepo>,
    onBackClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(repos.itemCount) { index ->
                repos[index]?.let { repo ->
                    Text(text = repo.fullName, modifier = Modifier.padding(8.dp))
                }
            }
        }

        repos.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    )
                }
                loadState.append is LoadState.Error -> {
                    val e = loadState.append as LoadState.Error
                    Text("Error: ${e.error.message}", color = Color.Red, modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}