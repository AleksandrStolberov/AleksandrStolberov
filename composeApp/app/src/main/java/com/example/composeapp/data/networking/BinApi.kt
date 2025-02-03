package com.example.composeapp.data.networking

import com.example.composeapp.data.model.CardResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BinApi {

    @GET("/{bin}")
    suspend fun getInfoByBin(@Path("bin") bin: Int): CardResponse

}