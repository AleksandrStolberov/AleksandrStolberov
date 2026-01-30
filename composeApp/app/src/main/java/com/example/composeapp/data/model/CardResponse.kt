package com.example.composeapp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardResponse(
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: Country?,
    val bank: Bank?
)

@JsonClass(generateAdapter = true)
data class Bank(
    val name: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)

@JsonClass(generateAdapter = true)
data class Country(
    val alpha2: String?,
    val name: String?,
    val latitude: Long?,
    val longitude: Long?
)