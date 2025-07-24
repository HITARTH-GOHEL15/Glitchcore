package com.example.hd_1.data.rawgModels


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddedByStatusModel(
    @SerialName("beaten")
    val beaten: Int? = 0,
    @SerialName("dropped")
    val dropped: Int? = 0,
    @SerialName("owned")
    val owned: Int? = 0,
    @SerialName("playing")
    val playing: Int? = 0,
    @SerialName("toplay")
    val toplay: Int? = 0,
    @SerialName("yet")
    val yet: Int? = 0
)