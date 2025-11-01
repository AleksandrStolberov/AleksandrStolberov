package com.example.rickandmorty.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageResponse(
    val info: PageInfoResponse,
    val results: List<CharacterResponse>
)

@JsonClass(generateAdapter = true)
data class CharacterResponse(
    val id: Long,
    val name: String,
    val gender: String,
    val status: String,
    val type: String,
    val image: String
)

@JsonClass(generateAdapter = true)
data class PageInfoResponse(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String?
)