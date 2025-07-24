package com.example.hd_1.data.rawgModels


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScreenshotModel(
    @SerialName("height")
    val height: Int? = 0,
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("image")
    val image: String? = "",
    @SerialName("is_deleted")
    val isDeleted: Boolean? = false,
    @SerialName("width")
    val width: Int? = 0
)