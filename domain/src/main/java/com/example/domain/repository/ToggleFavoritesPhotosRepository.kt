package com.example.domain.repository

import com.example.domain.entity.Photos

interface ToggleFavoritesPhotosRepository {

    suspend fun addToFavorites(photos: Photos)

    suspend fun removeFromFavorites(id: String): Int?

    suspend fun isFavorite(id: String): Boolean
}