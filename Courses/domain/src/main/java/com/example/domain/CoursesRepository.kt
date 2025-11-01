package com.example.domain

interface CoursesRepository {

    suspend fun getCourses(): List<Course>

    suspend fun getFavoriteCourses(): List<Course>

    suspend fun insertFavoriteCourse(course: Course)

    suspend fun deleteFavoriteCourse(id: Long)

}