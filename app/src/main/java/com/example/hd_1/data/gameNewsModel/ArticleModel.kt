package com.example.hd_1.data.gameNewsModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleModel(
    @SerialName("author")
    val author: String? = "",
    @SerialName("content")
    val content: String? = "",
    @SerialName("description")
    val description: String? = "",
    @SerialName("publishedAt")
    val publishedAt: String? = "",
    @SerialName("source")
    val source: SourceModel? = SourceModel(),
    @SerialName("title")
    val title: String? = "",
    @SerialName("url")
    val url: String? = "",
    @SerialName("urlToImage")
    val urlToImage: String? = ""
)