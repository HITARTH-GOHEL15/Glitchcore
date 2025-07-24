package com.example.hd_1.data

import com.example.hd_1.data.rawgModels.DeveloperModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeveloperResponseModel(
    @SerialName("results")
    val developers: List<DeveloperModel> = emptyList(),
)
