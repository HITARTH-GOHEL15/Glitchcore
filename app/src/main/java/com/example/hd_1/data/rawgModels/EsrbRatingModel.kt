package com.example.hd_1.data.rawgModels


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EsrbRatingModel(
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("name")
    val name: String? = "",
    @SerialName("slug")
    val slug: String? = ""
)