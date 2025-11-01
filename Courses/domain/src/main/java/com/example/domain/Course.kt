package com.example.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Course(
    val id: Long,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    var hasLike: Boolean,
    val publishDate: String
) : Parcelable