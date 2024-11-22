package com.luanafernandes.emojiapp.data.mapper

import com.luanafernandes.emojiapp.data.remote.dto.Emoji

fun mapToEmojiList(emojiMap: Map<String, String>): List<Emoji> {
    return emojiMap.map { emoji ->
        Emoji(
            name = emoji.key,
            url = emoji.value
        )
    }
}