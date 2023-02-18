package com.example.data.dataSource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.dataSource.local.entity.FavoritesPhotosEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotosDao {

    @Query("SELECT * FROM favorites_photos")
     fun getAll(): ArrayList<FavoritesPhotosEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(favoritesPhotos: FavoritesPhotosEntity): Long

    @Query("DELETE FROM favorites_photos WHERE id = :photoId")
    fun deleteByPhotoId(photoId: String): Long

    @Query("SELECT EXISTS (SELECT 1 FROM favorites_photos WHERE id = :id)")
    fun isExist(id: String): Boolean

}