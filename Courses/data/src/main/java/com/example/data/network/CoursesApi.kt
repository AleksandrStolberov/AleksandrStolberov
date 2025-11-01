package com.example.data.network

import com.example.data.model.PageResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface CoursesApi {

    @GET
    suspend fun getCourses(
        @Url url: String = "https://drive.usercontent.google.com/u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download"
    ): PageResponse

}