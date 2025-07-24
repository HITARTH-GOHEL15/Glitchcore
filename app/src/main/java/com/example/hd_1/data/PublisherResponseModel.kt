package com.example.hd_1.data

import com.example.hd_1.data.rawgModels.PublisherModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PublisherResponseModel(
    @SerialName("results")
    val publishers: List<PublisherModel> = emptyList(),
)
