package com.example.domain.repository

import com.example.domain.entity.Photos

interface FavoritesPhotosRepository {

    suspend fun getAllFavoritesPhotos(): MutableList<Photos>?

}