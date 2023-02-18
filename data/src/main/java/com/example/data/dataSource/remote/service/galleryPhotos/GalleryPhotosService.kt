package com.example.data.dataSource.remote.service.galleryPhotos

import com.example.data.dataSource.remote.dto.FlickrPhotosDto


interface GalleryPhotosService {

    suspend fun callGalleryPhotos(userId: String): FlickrPhotosDto


}
