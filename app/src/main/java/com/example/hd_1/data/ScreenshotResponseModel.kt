package com.example.hd_1.data


import com.example.hd_1.data.rawgModels.ScreenshotModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScreenshotResponseModel(
    @SerialName("count")
    val count: Int? = 0,

    @SerialName("results")
    val results: List<ScreenshotModel> = listOf()
)