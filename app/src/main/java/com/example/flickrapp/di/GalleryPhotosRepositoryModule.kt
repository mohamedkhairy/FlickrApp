package com.example.flickrapp.di


import com.example.data.dataSource.remote.service.galleryPhotos.GalleryPhotosService
import com.example.data.repository.allPhotos.AllGalleryPhotosRepositoryImp
import com.example.domain.repository.AllGalleryPhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object GalleryPhotosRepositoryModule {

    @Provides
    fun provideAllGalleryPhotosRepository( clientService: GalleryPhotosService) : AllGalleryPhotosRepository {
        return AllGalleryPhotosRepositoryImp(clientService)
    }



}