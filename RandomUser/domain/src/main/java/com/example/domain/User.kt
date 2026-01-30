package com.example.domain

data class User(
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val dateOfBirth: Dob,
    val phone: String,
    val picture: Picture,
    val nat: String
)

data class Dob(
    val date: String,
    val age: String
)

data class Location(
    val street: Street,
    val city: String,
    val state: String,
    val country: String
)

data class Street(
    val number: String,
    val name: String
)

data class Name(
    val first: String,
    val last: String
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)