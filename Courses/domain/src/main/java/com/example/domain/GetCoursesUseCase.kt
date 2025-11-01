package com.example.domain

class GetCoursesUseCase (private val repository: CoursesRepository)  {

    suspend fun invoke() = repository.getCourses()

}