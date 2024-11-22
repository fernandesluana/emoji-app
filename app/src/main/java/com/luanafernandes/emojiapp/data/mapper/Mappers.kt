package com.luanafernandes.emojiapp.data.mapper

import com.luanafernandes.emojiapp.data.local.EmojiEntity
import com.luanafernandes.emojiapp.data.remote.dto.Emoji

fun mapToEmojiList(emojiMap: Map<String, String>): List<Emoji> {
    return emojiMap.map { emoji ->
        Emoji(
            name = emoji.key,
            url = emoji.value
        )
    }
}

fun mapToEmojiEntityList(emojis: List<Emoji>): List<EmojiEntity> {
    return emojis.map { emoji ->
        EmojiEntity(
            name = emoji.name,
            url = emoji.url
        )
    }
}