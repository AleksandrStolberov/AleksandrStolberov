package com.example.data.networking

import com.example.data.RandomUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {

    //https://randomuser.me/api/?gender=female/?nat=

    @GET("api/")
    suspend fun getRandomUser(
        @Query("gender") gender: String,
        @Query("nat") nat: String,
    ): RandomUserResponse

}

