package com.example.hd_1.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameResponseModel(
    @SerialName("results")
    val games: List<GameModel> = emptyList(),

    @SerialName("next")
    val next: String? = null,

    @SerialName("previous")
    val previous: String? = null,

    @SerialName("count")
    val count: Int? = null
)
