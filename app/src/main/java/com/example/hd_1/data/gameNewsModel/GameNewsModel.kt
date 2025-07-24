package com.example.hd_1.data.gameNewsModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameNewsModel(
    @SerialName("articles")
    val articles: List<ArticleModel> = listOf(),
    @SerialName("status")
    val status: String? = "",
    @SerialName("totalResults")
    val totalResults: Int? = 0
)