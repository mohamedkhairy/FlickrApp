package com.example.data.dataSource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_photos")
data class FavoritesPhotosEntity (
    @PrimaryKey
    val id: String,
    val title: String,
    val imageUrl: String
)