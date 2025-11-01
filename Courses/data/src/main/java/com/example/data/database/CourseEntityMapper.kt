package com.example.data.database

import com.example.data.model.CourseEntity
import com.example.domain.Course
import com.example.domain.Mapper

class CourseEntityMapper : Mapper<CourseEntity, Course>() {
    override suspend fun map(from: CourseEntity) = from.run {
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