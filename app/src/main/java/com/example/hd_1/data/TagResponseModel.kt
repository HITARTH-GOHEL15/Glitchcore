package com.example.hd_1.data

import com.example.hd_1.data.rawgModels.TagModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagResponseModel(
    @SerialName("results")
    val tags: List<TagModel> = emptyList()
)

