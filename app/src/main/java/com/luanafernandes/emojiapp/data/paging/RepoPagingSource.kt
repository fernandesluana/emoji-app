package com.luanafernandes.emojiapp.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.luanafernandes.emojiapp.data.mapper.toGitHubRepoList
import com.luanafernandes.emojiapp.data.remote.EmojiApiService
import com.luanafernandes.emojiapp.domain.model.GitHubRepo


private const val INITIAL_PAGE = 1

class RepoPagingSource(
    private val api: EmojiApiService,
    private val username: String
) : PagingSource<Int, GitHubRepo>() {

    override fun getRefreshKey(state: PagingState<Int, GitHubRepo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitHubRepo> {
        return try {
            val page = params.key ?: INITIAL_PAGE
            val response = api.getUserRepos(username, page, params.loadSize)
            Log.d("RepoPagingSource", "Response: $response")
            LoadResult.Page(
                data = response.toGitHubRepoList(),
                prevKey = if (page == INITIAL_PAGE) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}