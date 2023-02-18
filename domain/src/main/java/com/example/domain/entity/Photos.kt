package com.example.domain.entity

data class Photos (
    val id: Int,
    val title: String,
    val image: String,
    val farmId: String,
    val secret: String,
    val serverId: String
)