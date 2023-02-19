package com.example.flickrapp.di

import android.content.Context
import androidx.room.Room
import com.example.data.dataSource.local.PhotosDao
import com.example.data.dataSource.local.PhotosDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : PhotosDatabase =
        Room.databaseBuilder(context, PhotosDatabase::class.java, "favorite_photos_database")
            .build()

    @Provides
    @Singleton
    fun providePhotosDao(database: PhotosDatabase) : PhotosDao =
        database.favoritesPhotosDao()

}