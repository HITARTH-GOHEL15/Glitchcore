package com.example.hd_1.data.rawgModels


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetacriticPlatformModel(
    @SerialName("metascore")
    val metascore: Int? = 0,
    @SerialName("platform")
    val platform: PlatformModel? = PlatformModel(),
    @SerialName("url")
    val url: String? = ""
)