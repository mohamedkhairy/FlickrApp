package com.example.data.dataSource.remote.service.galleryPhotos

import com.example.data.dataSource.remote.dto.FlickrPhotosDto
import com.example.data.dataSource.remote.service.EndPoints
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class GalleryPhotosServiceImpl @Inject constructor(val httpClient: HttpClient):
    GalleryPhotosService {

    override suspend fun callGalleryPhotos(userId: String): FlickrPhotosDto {
        return httpClient.get<FlickrPhotosDto> {
            url(EndPoints.getEndpointUrl())
        }
    }


}