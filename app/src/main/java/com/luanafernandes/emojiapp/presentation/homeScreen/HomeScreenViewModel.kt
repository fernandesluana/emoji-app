package com.luanafernandes.emojiapp.presentation.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luanafernandes.emojiapp.data.remote.dto.Emoji
import com.luanafernandes.emojiapp.domain.repository.EmojiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: EmojiRepository
): ViewModel() {

    private val _emojis = MutableStateFlow<List<Emoji>>(emptyList())
    val emojis: StateFlow<List<Emoji>> get() = _emojis

    init {
        fetchEmojis()
    }

    fun fetchEmojis() {
        println("Fetching emojis...")
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


}