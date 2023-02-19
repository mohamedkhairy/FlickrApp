package com.example.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photos (
    val id: String,
    val title: String,
    val imageUrl: String
): Parcelable