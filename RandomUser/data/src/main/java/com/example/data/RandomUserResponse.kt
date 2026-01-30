package com.example.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RandomUserResponse(
    @Json(name = "results")
    val results: List<UserResponse>
)
