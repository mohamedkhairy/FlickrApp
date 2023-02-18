package com.example.data.repository.favorites

import com.example.data.dataSource.local.PhotosDao
import com.example.data.dataSource.utils.mapToFavoritePhoto
import com.example.domain.entity.Photos
import com.example.domain.repository.ToggleFavoritesPhotosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ToggleFavoritesPhotosRepositoryImp @Inject constructor(private val photosDto: PhotosDao) : ToggleFavoritesPhotosRepository {


    override suspend fun addToFavorites(photos: Photos): Long {
        return withContext(Dispatchers.IO){
            photosDto.save(photos.mapToFavoritePhoto())
        }
    }

    override suspend fun removeFromFavorites(id: String): Long {
        return withContext(Dispatchers.IO){
            photosDto.deleteByPhotoId(id)
        }
    }

    override suspend fun isFavorite(id: String): Boolean {
        return withContext(Dispatchers.IO){
            photosDto.isExist(id)
        }
    }

}