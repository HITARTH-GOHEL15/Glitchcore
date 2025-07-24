package com.example.hd_1.data.streamModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediumModel(
    @SerialName("height")
    val height: Int? = 0,
    @SerialName("url")
    val url: String? = "",
    @SerialName("width")
    val width: Int? = 0
)