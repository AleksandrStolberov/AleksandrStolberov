package com.example.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "gender")
    val gender: String,
    @Json(name = "name")
    val name: Name,
    @Json(name = "location")
    val location: Location,
    @Json(name = "email")
    val email: String,
    @Json(name = "dob")
    val dateOfBirth: Dob,
    @Json(name = "phone")
    val phone: String,
    @Json(name = "picture")
    val picture: Picture,
    val nat: String
)

@JsonClass(generateAdapter = true)
data class Dob(
    @Json(name = "date")
    val date: String,
    @Json(name = "age")
    val age: Int
)

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "street")
    val street: Street,
    @Json(name = "city")
    val city: String,
    @Json(name = "state")
    val state: String,
    @Json(name = "country")
    val country: String
)

@JsonClass(generateAdapter = true)
data class Street(
    @Json(name = "number")
    val number: Int,
    @Json(name = "name")
    val name: String
)

@JsonClass(generateAdapter = true)
data class Name(
    @Json(name = "first")
    val first: String,
    @Json(name = "last")
    val last: String
)

@JsonClass(generateAdapter = true)
data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)

