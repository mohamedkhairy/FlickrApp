package com.example.data.dataSource.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Photos(
    @SerialName("page")
    val page: Int,
    @SerialName("pages")
    val pages: Int,
    @SerialName("perpage")
    val perpage: Int,
    @SerialName("photo")
    val photo: List<Photo>,
    @SerialName("total")
    val total: Int
)