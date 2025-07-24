package com.example.hd_1.data.rawgModels


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlatformModelXX(
    @SerialName("platform")
    val platform: PlatformModel? = PlatformModel(),
    @SerialName("released_at")
    val releasedAt: String? = "",
    @SerialName("requirements")
    val requirements: RequirementsModel? = RequirementsModel()
)