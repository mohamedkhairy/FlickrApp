package com.example.data.dataSource.remote.service

import com.example.data.BuildConfig
import com.example.data.dataSource.remote.dto.Photo

object EndPoints {

    const val BASE_URL = "https://www.flickr.com/services/rest/"

    const val ALL_GALLERY_PHOTO = "$BASE_URL?method=flickr.galleries.getPhotos"
    const val GALLERY_ID = "66911286-72157647277042064"



    fun getEndpointUrl(): String =
        "${ALL_GALLERY_PHOTO}&api_key=${BuildConfig.API_KEY}&gallery_id=${GALLERY_ID}&format=json&nojsoncallback=1"

    fun buildImageUrl(photo: Photo): String =
        "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"
}


//https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg


//https://www.flickr.com/services/rest/?method=flickr.galleries.getPhotos&api_key=2b9187706448bd6ced89d10d757aa38d&gallery_id=66911286-72157647277042064&format=json&nojsoncallback=1