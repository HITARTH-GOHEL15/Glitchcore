package com.example.hd_1.data

import com.example.hd_1.data.gameNewsModel.GameNewsModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameNewsResponseModel(
    @SerialName("results")
    val gameNews: List<GameNewsModel> = emptyList()
)
