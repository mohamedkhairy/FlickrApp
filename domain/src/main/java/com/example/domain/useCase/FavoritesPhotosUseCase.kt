package com.example.domain.useCase

import arrow.core.Either
import com.example.domain.core.DataState
import com.example.domain.entity.Photos
import com.example.domain.repository.AllGalleryPhotosRepository
import com.example.domain.repository.FavoritesPhotosRepository
import com.example.domain.repository.ToggleFavoritesPhotosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class FavoritesPhotosUseCase @Inject constructor(
    private val favoritesPhotosRepository: FavoritesPhotosRepository
) {


    suspend fun invoke(): Flow<DataState<MutableList<Photos>?>> =
        flow {
            try {

                val result = favoritesPhotosRepository.getAllFavoritesPhotos()
                emit(DataState.Success(result))

            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }.onStart {
            emit(DataState.Loading())
        }
            .flowOn(Dispatchers.IO)



}