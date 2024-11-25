package com.luanafernandes.emojiapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GitHubRepoDto(
    val id: Int,
    @SerializedName("full_name")val fullName: String
)
