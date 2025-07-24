package com.example.hd_1.data

import com.example.hd_1.data.rawgModels.PlatformModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlatformResponseModel(
    @SerialName("results")
    val platforms: List<PlatformModel> = emptyList(),
)
