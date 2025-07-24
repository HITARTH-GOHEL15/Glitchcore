package com.example.hd_1.data.youtubeTrailerModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SnippetModel(
    @SerialName("channelId")
    val channelId: String? = "",
    @SerialName("channelTitle")
    val channelTitle: String? = "",
    @SerialName("description")
    val description: String? = "",
    @SerialName("liveBroadcastContent")
    val liveBroadcastContent: String? = "",
    @SerialName("publishTime")
    val publishTime: String? = "",
    @SerialName("publishedAt")
    val publishedAt: String? = "",
    @SerialName("thumbnails")
    val thumbnails: ThumbnailsModel? = ThumbnailsModel(),
    @SerialName("title")
    val title: String? = ""
)