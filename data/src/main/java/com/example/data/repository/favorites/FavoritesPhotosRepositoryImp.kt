package com.example.data.repository.favorites

import com.example.data.dataSource.local.PhotosDao
import com.example.data.dataSource.utils.mapToDomainPhotos
import com.example.domain.entity.Photos
import com.example.domain.repository.FavoritesPhotosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoritesPhotosRepositoryImp @Inject constructor(private val photosDto: PhotosDao)
    : FavoritesPhotosRepository {

    override suspend fun getAllFavoritesPhotos(): MutableList<Photos>? {
        return withContext(Dispatchers.IO){
            val savedItems = photosDto.getAll()
            savedItems.mapToDomainPhotos()
        }
    }




}