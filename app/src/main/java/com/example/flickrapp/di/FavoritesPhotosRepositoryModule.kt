package com.example.flickrapp.di

import com.example.data.dataSource.local.PhotosDao
import com.example.data.repository.favorites.FavoritesPhotosRepositoryImp
import com.example.domain.repository.FavoritesPhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object FavoritesPhotosRepositoryModule {

    @Provides
    fun provideFavoritesPhotosRepository(photosDao: PhotosDao) : FavoritesPhotosRepository {
        return FavoritesPhotosRepositoryImp(photosDao)
    }

}
