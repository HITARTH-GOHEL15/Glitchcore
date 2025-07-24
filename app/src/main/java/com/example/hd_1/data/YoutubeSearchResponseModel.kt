package com.example.hd_1.data

import com.example.hd_1.data.youtubeTrailerModel.YoutubeTrailerModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YoutubeSearchResponseModel(
    @SerialName("results")
    val youtubeTrailers: List<YoutubeTrailerModel> = emptyList()
)