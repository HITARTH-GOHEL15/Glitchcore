package com.example.hd_1.data.streamModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IdModel(
    @SerialName("channelId")
    val channelId: String? = "",
    @SerialName("kind")
    val kind: String? = "",
    @SerialName("videoId")
    val videoId: String? = ""
)