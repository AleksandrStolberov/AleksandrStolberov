package com.example.domain

class GetFavoriteCoursesUseCase (private val repository: CoursesRepository) {

    suspend fun invoke() = repository.getFavoriteCourses()

    suspend fun insertFavoriteCourse(course: Course) {
        repository.insertFavoriteCourse(course)
    }

    suspend fun deleteFavoriteCourse(id: Long) {
        repository.deleteFavoriteCourse(id)
    }

}