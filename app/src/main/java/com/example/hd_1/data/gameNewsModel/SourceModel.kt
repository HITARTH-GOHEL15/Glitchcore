package com.example.hd_1.data.gameNewsModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceModel(
    @SerialName("id")
    val id: String? = "",
    @SerialName("name")
    val name: String? = ""
)