package com.example.flickrapp.di


import com.example.data.dataSource.remote.service.galleryPhotos.GalleryPhotosService
import com.example.data.dataSource.remote.service.galleryPhotos.GalleryPhotosServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.ktor.client.*

@Module
@InstallIn(ViewModelComponent::class)
object ServiceModule {

    @Provides
    fun provideGalleryPhotosService(httpClient: HttpClient): GalleryPhotosService {
        return GalleryPhotosServiceImpl(httpClient)
    }

}