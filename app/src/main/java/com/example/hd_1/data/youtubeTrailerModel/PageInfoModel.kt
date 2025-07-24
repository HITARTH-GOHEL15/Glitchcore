package com.example.hd_1.data.youtubeTrailerModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageInfoModel(
    @SerialName("resultsPerPage")
    val resultsPerPage: Int? = 0,
    @SerialName("totalResults")
    val totalResults: Int? = 0
)