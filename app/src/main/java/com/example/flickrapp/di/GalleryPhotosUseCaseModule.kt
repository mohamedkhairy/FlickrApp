package com.example.flickrapp.di

import com.example.domain.repository.AllGalleryPhotosRepository
import com.example.domain.useCase.AllGalleryPhotosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object GalleryPhotosUseCaseModule {

    @Provides
    fun provideAllGalleryPhotosUseCase(allGalleryPhotosRepository: AllGalleryPhotosRepository): AllGalleryPhotosUseCase {
        return AllGalleryPhotosUseCase(allGalleryPhotosRepository)
    }

}