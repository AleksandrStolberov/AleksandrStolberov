package com.example.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageResponse(
   val courses: List<CourseResponse>
)

@JsonClass(generateAdapter = true)
data class CourseResponse(
    val id: Long,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)

//id - идентификатор курса
//title - заголовок курса
//text - описание курса.
//price - цена курса
//rate - рейтинг курса
//startDate - дата начала курса
//hasLike - признак, добавлен ли курс в избранное
//publishDate - дата публикации курса