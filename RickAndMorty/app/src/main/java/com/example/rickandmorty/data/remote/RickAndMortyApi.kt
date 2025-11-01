package com.example.rickandmorty.data.remote

import com.example.rickandmorty.data.model.PageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {


    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): PageResponse

    @GET("character")
    suspend fun getCharacterByName(
        @Query("name") name: String
    ): PageResponse

}