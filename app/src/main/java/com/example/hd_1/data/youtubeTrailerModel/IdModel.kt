package com.example.hd_1.data.youtubeTrailerModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IdModel(
    @SerialName("kind")
    val kind: String? = "",
    @SerialName("videoId")
    val videoId: String? = ""
)