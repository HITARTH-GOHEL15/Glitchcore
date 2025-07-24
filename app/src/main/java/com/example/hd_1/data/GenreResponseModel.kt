package com.example.hd_1.data

import com.example.hd_1.data.rawgModels.GenreModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreResponseModel(
    @SerialName("results")
    val genres: List<GenreModel> = emptyList(),

    )