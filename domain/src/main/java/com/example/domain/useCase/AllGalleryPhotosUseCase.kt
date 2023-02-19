package com.example.domain.useCase

import arrow.core.Either
import com.example.domain.core.DataState
import com.example.domain.entity.Photos
import com.example.domain.repository.AllGalleryPhotosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class AllGalleryPhotosUseCase @Inject constructor(
    private val allGalleryPhotosRepository: AllGalleryPhotosRepository
) {

    suspend fun invoke(): Flow<DataState<MutableList<Photos>?>> =
        flow {
            try {
                val result = allGalleryPhotosRepository.getAllGalleryPhotos()
                when (result) {
                    is Either.Right -> {

                        emit(DataState.Success(result.value))
                    }

                    is Either.Left -> {

                        emit(DataState.Error(result.value))
                    }
                }

            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }.onStart {
            emit(DataState.Loading())
        }
            .flowOn(Dispatchers.IO)


}
