package com.example.domain.repository

import com.example.domain.entity.Photos

interface ToggleFavoritesPhotosRepository {

    suspend fun addToFavorites(photos: Photos): Long

    suspend fun removeFromFavorites(id: String): Long

    suspend fun isFavorite(id: String): Boolean
}