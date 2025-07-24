package com.example.hd_1.data.streamModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YoutubeStreamModel(
    @SerialName("etag")
    val etag: String? = "",
    @SerialName("items")
    val items: List<StreamItemModel> = listOf(),
    @SerialName("kind")
    val kind: String? = "",
    @SerialName("nextPageToken")
    val nextPageToken: String? = "",
    @SerialName("pageInfo")
    val pageInfo: PageInfoModel? = PageInfoModel(),
    @SerialName("regionCode")
    val regionCode: String? = ""
)