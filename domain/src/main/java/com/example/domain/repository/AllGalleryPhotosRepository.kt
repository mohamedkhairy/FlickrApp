package com.example.domain.repository

import arrow.core.Either
import com.example.domain.entity.Photos
import kotlinx.coroutines.flow.Flow

interface AllGalleryPhotosRepository {

    suspend fun getAllGalleryPhotos(): Either<Exception, MutableList<Photos>?>

}