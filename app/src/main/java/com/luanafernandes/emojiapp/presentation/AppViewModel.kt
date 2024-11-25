package com.luanafernandes.emojiapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.luanafernandes.emojiapp.domain.model.Emoji
import com.luanafernandes.emojiapp.domain.model.GitHubRepo
import com.luanafernandes.emojiapp.domain.model.User
import com.luanafernandes.emojiapp.domain.repository.EmojiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repository: EmojiRepository
) : ViewModel() {

    private val _emojis = MutableStateFlow<List<Emoji>>(emptyList())
    val emojis: StateFlow<List<Emoji>> get() = _emojis

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> get() = _users

    init {
        fetchEmojis()
        fetchUsers()
    }

    fun fetchEmojis() {
        viewModelScope.launch {
            try {
                val result = repository.getAllEmojis()
                _emojis.value = result
                println("Emojis: $result")
            } catch (e: Exception) {
                e.printStackTrace()
                println("Error fetching emojis: ${e.message}")
            }
        }
    }

    fun removeEmojiFromList(emoji: Emoji) {
        _emojis.value = _emojis.value.filterNot { it == emoji }
    }

    fun fetchUser(username: String) {
        viewModelScope.launch {
            try {
                val user = repository.getUser(username)
                _user.value = user
                println("User: $user")
            } catch (e: Exception) {
                e.printStackTrace()
                println("Error fetching user: ${e.message}")
            }
        }
    }

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val usersFromDb = repository.getAllUsers()
                _users.value = usersFromDb
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteUser(username: String) {
        viewModelScope.launch {
            try {
                repository.deleteUser(username)
                _users.value = _users.value.filterNot { it.login == username }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    val repos: Flow<PagingData<GitHubRepo>> = repository.getUserRepos()
        .cachedIn(viewModelScope)

}