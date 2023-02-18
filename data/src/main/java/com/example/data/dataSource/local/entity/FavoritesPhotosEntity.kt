package com.example.data.dataSource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_photos")
data class FavoritesPhotosEntity (
    @PrimaryKey
    val id: Int,
    val title: String,
    val image: String,
    val farmId: String,
    val secret: String,
    val serverId: String
)