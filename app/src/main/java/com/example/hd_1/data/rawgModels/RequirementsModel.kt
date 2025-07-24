package com.example.hd_1.data.rawgModels


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequirementsModel(
    @SerialName("minimum")
    val minimum: String? = "",
    @SerialName("recommended")
    val recommended: String? = ""
)