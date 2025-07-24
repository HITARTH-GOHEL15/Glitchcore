package com.example.hd_1.data.rawgModels


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RatingModel(
    @SerialName("count")
    val count: Int? = 0,
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("percent")
    val percent: Double? = 0.0,
    @SerialName("title")
    val title: String? = ""
)