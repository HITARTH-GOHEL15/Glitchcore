package com.example.hd_1.data.streamModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StreamItemModel(
    @SerialName("etag")
    val etag: String? = "",
    @SerialName("id")
    val id: IdModel? = IdModel(),
    @SerialName("kind")
    val kind: String? = "",
    @SerialName("snippet")
    val snippet: SnippetModel? = SnippetModel()
)