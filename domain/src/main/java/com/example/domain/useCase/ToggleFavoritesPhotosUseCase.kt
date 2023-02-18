package com.example.domain.useCase

import com.example.domain.core.DataState
import com.example.domain.entity.Photos
import com.example.domain.repository.AllGalleryPhotosRepository
import com.example.domain.repository.ToggleFavoritesPhotosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ToggleFavoritesPhotosUseCase @Inject constructor(
    private val toggleFavoritesPhotosRepository: ToggleFavoritesPhotosRepository
) {


    suspend fun invoke(isAddFavorite: Boolean, photos: Photos): Flow<DataState<Long>> =
        flow {
            try {

                if (isAddFavorite) {
                    val result = toggleFavoritesPhotosRepository.addToFavorites(photos)
                    emit(DataState.Success(result))
                }else{
                    val result = toggleFavoritesPhotosRepository.removeFromFavorites(photos.id)
                    emit(DataState.Success(result))
                }

            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }.onStart {
            emit(DataState.Loading())
        }
            .flowOn(Dispatchers.IO)


    suspend fun isFavorite(id: String): Flow<DataState<Boolean>> =
        flow {
            try {
                    val result = toggleFavoritesPhotosRepository.isFavorite(id)
                    emit(DataState.Success(result))

            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
            .flowOn(Dispatchers.IO)
}