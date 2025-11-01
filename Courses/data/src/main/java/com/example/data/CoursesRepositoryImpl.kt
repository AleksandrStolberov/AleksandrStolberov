package com.example.data

import android.util.Log
import com.example.data.database.CourseEntityMapper
import com.example.data.database.CoursesDao
import com.example.data.model.CourseEntity
import com.example.data.model.CourseResponse
import com.example.data.network.CourseMapper
import com.example.data.network.CoursesApi
import com.example.domain.Course
import com.example.domain.CoursesRepository
import com.example.domain.Mapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(val api: CoursesApi, val dao: CoursesDao) :
    CoursesRepository {

    val mapper: Mapper<CourseResponse, Course> = CourseMapper()
    val mapperEntity: Mapper<CourseEntity, Course> = CourseEntityMapper()

    override suspend fun getCourses(): List<Course> {
        return try {
            val courses = api.getCourses().courses.map {
                Log.d("CoursesInformation", it.toString())
                mapper.map(it)
            }
            for (i in courses) {
                val course: CourseEntity? = dao.getFavoriteCourse(i.id)
                i.hasLike = course != null
            }
            courses
        } catch (e: Exception) {
            Log.d("CoursesInformation", e.message.toString())
            emptyList()
        }
    }

    override suspend fun getFavoriteCourses(): List<Course> {
        return withContext(Dispatchers.IO) {
            dao.getAllCourses().map {
                Log.d("FavoriteCourses", it.toString())
                mapperEntity.map(it)
            }
        }
    }

    override suspend fun insertFavoriteCourse(course: Course) {
        dao.insertFavoriteCourse(
            CourseEntity(
                id = course.id,
                title = course.title,
                text = course.text,
                price = course.price,
                rate = course.rate,
                startDate = course.startDate,
                hasLike = course.hasLike,
                publishDate = course.publishDate
            )
        )
    }

    override suspend fun deleteFavoriteCourse(id: Long) {
        dao.removeFavoriteCourse(id)
    }
}