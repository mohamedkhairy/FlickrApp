package com.example.flickrapp.di

import com.example.domain.repository.FavoritesPhotosRepository
import com.example.domain.repository.ToggleFavoritesPhotosRepository
import com.example.domain.useCase.FavoritesPhotosUseCase
import com.example.domain.useCase.ToggleFavoritesPhotosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object FavoriteUseCasesModule {


    @Provides
    fun provideFavoritesPhotosUseCase(favoritesPhotosRepository: FavoritesPhotosRepository): FavoritesPhotosUseCase {
        return FavoritesPhotosUseCase(favoritesPhotosRepository)
    }

    @Provides
    fun provideToggleFavoritesPhotosUseCase(toggleFavoritesPhotosRepository: ToggleFavoritesPhotosRepository): ToggleFavoritesPhotosUseCase {
        return ToggleFavoritesPhotosUseCase(toggleFavoritesPhotosRepository)
    }


}