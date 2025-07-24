package com.example.hd_1.data.rawgModels


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParentPlatformModel(
    @SerialName("platform")
    val platform: PlatformModelX? = PlatformModelX()
)