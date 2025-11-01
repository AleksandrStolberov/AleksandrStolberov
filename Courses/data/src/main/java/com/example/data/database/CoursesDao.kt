package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.CourseEntity

@Dao
interface CoursesDao {

    @Query("SELECT * FROM ${DatabaseInfo.TABLE_NAME}")
    fun getAllCourses(): List<CourseEntity>

    @Query("SELECT * FROM ${DatabaseInfo.TABLE_NAME} WHERE id = :id")
    suspend fun getFavoriteCourse(id: Long): CourseEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteCourse(item: CourseEntity)

    @Query("DELETE FROM ${DatabaseInfo.TABLE_NAME}")
    suspend fun clearAll()

    @Query("DELETE FROM ${DatabaseInfo.TABLE_NAME} WHERE id = :id")
    suspend fun removeFavoriteCourse(id: Long)

}