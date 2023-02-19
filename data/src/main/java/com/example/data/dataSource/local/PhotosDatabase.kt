package com.example.data.dataSource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.dataSource.local.entity.FavoritesPhotosEntity


@Database(entities = [FavoritesPhotosEntity::class], version = 1)
abstract class PhotosDatabase : RoomDatabase() {

    abstract fun favoritesPhotosDao(): PhotosDao

}
