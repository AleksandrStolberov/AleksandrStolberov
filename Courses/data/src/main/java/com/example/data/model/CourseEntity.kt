package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.database.DatabaseInfo

@Entity(tableName = DatabaseInfo.TABLE_NAME)
data class CourseEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)