package com.example.data.dataSource.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    @SerialName("farm")
    val farm: Int,
    @SerialName("has_comment")
    val hasComment: Int,
    @SerialName("id")
    val id: String,
    @SerialName("is_primary")
    val isPrimary: Int,
    @SerialName("isfamily")
    val isfamily: Int,
    @SerialName("isfriend")
    val isfriend: Int,
    @SerialName("ispublic")
    val ispublic: Int,
    @SerialName("owner")
    val owner: String,
    @SerialName("secret")
    val secret: String,
    @SerialName("server")
    val server: String,
    @SerialName("title")
    val title: String
)