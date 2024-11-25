package com.luanafernandes.emojiapp.data.mapper

import com.luanafernandes.emojiapp.data.local.emoji.EmojiEntity
import com.luanafernandes.emojiapp.data.local.user.UserEntity
import com.luanafernandes.emojiapp.data.remote.dto.EmojiDto
import com.luanafernandes.emojiapp.data.remote.dto.GitHubRepoDto
import com.luanafernandes.emojiapp.data.remote.dto.UserDto
import com.luanafernandes.emojiapp.domain.model.Emoji
import com.luanafernandes.emojiapp.domain.model.GitHubRepo
import com.luanafernandes.emojiapp.domain.model.User

//Emoji Mappers

fun mapToEmojiList(emojiMap: Map<String, String>): List<EmojiDto> {
    return emojiMap.map { emoji ->
        EmojiDto(
            name = emoji.key,
            url = emoji.value
        )
    }
}

fun emojiDtoListToEmojiEntityList(emojis: List<EmojiDto>): List<EmojiEntity> {
    return emojis.map { emoji ->
        EmojiEntity(
            name = emoji.name,
            url = emoji.url
        )
    }
}

fun emojiEntityListToEmojiList(emojiEntities: List<EmojiEntity>): List<Emoji> {
    return emojiEntities.map { emojiEntity ->
        Emoji(
            name = emojiEntity.name,
            url = emojiEntity.url
        )
    }
}

fun dtoListToEmojiList(emojiDtoList: List<EmojiDto>): List<Emoji> {
    return emojiDtoList.map { emojiDto ->
        Emoji(
            name = emojiDto.name,
            url = emojiDto.url
        )
    }

}

//User Mappers

fun userDtoToUserEntity(userDto: UserDto): UserEntity {
    return UserEntity(
        login = userDto.login,
        id = userDto.id,
        avatarUrl = userDto.avatarUrl
    )
}

fun userEntityToUser(userEntity: UserEntity) : User {
    return User(
        login = userEntity.login,
        id = userEntity.id,
        avatarUrl = userEntity.avatarUrl
    )
}

//GitHubRepo Mappers

fun repoListDtoToGitHubRepoList(repoDtoList: List<GitHubRepoDto>): List<GitHubRepo> {
    return repoDtoList.map { repoDto ->
        GitHubRepo(
            id = repoDto.id,
            fullName = repoDto.fullName
        )
    }
}

fun List<GitHubRepoDto>.toGitHubRepoList(): List<GitHubRepo> {
    return map { repoDto ->
        GitHubRepo(
            id = repoDto.id,
            fullName = repoDto.fullName
        )
    }
}
