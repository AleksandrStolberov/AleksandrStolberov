package com.example.rickandmorty.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharactersEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val gender: String,
    val status: String,
    val type: String,
    val image: String
)
