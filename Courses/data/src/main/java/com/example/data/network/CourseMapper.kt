package com.example.data.network

import com.example.data.model.CourseResponse
import com.example.domain.Course
import com.example.domain.Mapper

class CourseMapper : Mapper<CourseResponse, Course>() {
    override suspend fun map(from: CourseResponse) = from.run {
        Course(
            id = id,
            title = title,
            text = text,
            price = price,
            rate = rate,
            startDate = startDate,
            hasLike = hasLike,
            publishDate = publishDate
        )
    }
}