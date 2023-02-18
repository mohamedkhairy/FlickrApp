package com.example.data.dataSource.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlickrPhotosDto(
    @SerialName("photos")
    val photos: Photos,
    @SerialName("stat")
    val stat: String
)