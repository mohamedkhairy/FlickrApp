package com.example.flickrapp.di

import com.example.data.dataSource.local.PhotosDao
import com.example.data.repository.favorites.ToggleFavoritesPhotosRepositoryImp
import com.example.domain.repository.ToggleFavoritesPhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object ToggleFavoritesRepositoryModule {


    @Provides
    fun provideToggleFavoritesPhotosRepository(photosDto: PhotosDao): ToggleFavoritesPhotosRepository {
        return ToggleFavoritesPhotosRepositoryImp(photosDto)
    }



}