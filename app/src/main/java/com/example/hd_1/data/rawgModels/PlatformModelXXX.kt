package com.example.hd_1.data.rawgModels


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlatformModelXXX(
    @SerialName("name")
    val name: String? = "",
    @SerialName("platform")
    val platform: Int? = 0,
    @SerialName("slug")
    val slug: String? = ""
)