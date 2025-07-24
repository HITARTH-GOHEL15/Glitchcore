package com.example.hd_1.data

import com.example.hd_1.data.rawgModels.StoreModelX
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreResponseModel(
    @SerialName("results")
    val stores: List<StoreModelX>
)
