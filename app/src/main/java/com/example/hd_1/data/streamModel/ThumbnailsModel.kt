package com.example.hd_1.data.streamModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ThumbnailsModel(
    @SerialName("default")
    val default: DefaultModel? = DefaultModel(),
    @SerialName("high")
    val high: HighModel? = HighModel(),
    @SerialName("medium")
    val medium: MediumModel? = MediumModel()
)