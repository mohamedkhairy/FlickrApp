package com.example.domain.useCase

import com.example.domain.core.DataState
import com.example.domain.entity.Photos
import com.example.domain.repository.FavoritesPhotosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class FavoritesPhotosUseCase @Inject constructor(
    private val favoritesPhotosRepository: FavoritesPhotosRepository
) {


    suspend fun invoke(): Flow<DataState<List<Photos>?>> =
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