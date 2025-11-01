package com.example.rickandmorty.domain

data class Character(
    val name: String,
    val gender: String,
    val type: String,
    val status: CharacterStatus,
    val image: String
)