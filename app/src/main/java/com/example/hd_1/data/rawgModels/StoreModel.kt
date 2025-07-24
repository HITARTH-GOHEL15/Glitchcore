package com.example.hd_1.data.rawgModels


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreModel(
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("store")
    val store: StoreModelX? = StoreModelX(),
    @SerialName("url")
    val url: String? = ""
)