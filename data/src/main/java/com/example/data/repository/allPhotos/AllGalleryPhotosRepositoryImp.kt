package com.example.data.repository.allPhotos

import arrow.core.Either
import com.example.data.dataSource.remote.service.galleryPhotos.GalleryPhotosService
import com.example.data.dataSource.utils.mapToDomainPhotos
import com.example.domain.entity.Photos
import com.example.domain.repository.AllGalleryPhotosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AllGalleryPhotosRepositoryImp @Inject constructor(private val service: GalleryPhotosService) :
    AllGalleryPhotosRepository {

    override suspend fun getAllGalleryPhotos(): Either<Exception, MutableList<Photos>?> {
        return withContext(Dispatchers.IO) {
            val either = try {
                val remoteItems = service.callGalleryPhotos()
                val photosList = remoteItems.photos.photo.mapToDomainPhotos()
                Either.Right(photosList)
            } catch (e: Exception) {
                e.printStackTrace()
                Either.Left(e)
            }
            either
        }
    }
}