package com.example.data.dataSource.utils

import com.example.data.dataSource.local.entity.FavoritesPhotosEntity
import com.example.data.dataSource.remote.dto.Photo
import com.example.data.dataSource.remote.service.EndPoints.buildImageUrl
import com.example.domain.entity.Photos


fun List<FavoritesPhotosEntity>?.mapToFavoritesDomainPhotos(): List<Photos>? =
    this?.toMutableList()?.map {
        Photos(
            it.id,
            it.title,
            it.imageUrl
        )
    }


fun List<Photo>?.mapToDomainPhotos(): MutableList<Photos>? =
    this?.map {
        Photos(
            it.id.toString(),
            it.title,
            buildImageUrl(it)
        )
    }?.toMutableList()


fun Photos.mapToFavoritePhoto(): FavoritesPhotosEntity=
    FavoritesPhotosEntity(
        this.id,
        this.title,
        this.imageUrl
    )