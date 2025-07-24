package com.example.hd_1.data.rawgModels


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreModel(
    @SerialName("games_count")
    val gamesCount: Int? = 0,
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("image_background")
    val imageBackground: String? = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("slug")
    val slug: String? = ""
)