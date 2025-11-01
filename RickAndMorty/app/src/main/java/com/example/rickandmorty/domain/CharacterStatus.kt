package com.example.rickandmorty.domain

import androidx.compose.ui.graphics.Color

enum class CharacterStatus {
    ALIVE, DEAD, UNKNOWN
}

fun getCharacterStatusEnum(status: String) =
    when (status) {
        "Alive" -> CharacterStatus.ALIVE
        "Dead" -> CharacterStatus.DEAD
        else -> CharacterStatus.UNKNOWN
    }

fun getColor(status: CharacterStatus) =
    when (status) {
        CharacterStatus.ALIVE -> Color.Green
        CharacterStatus.DEAD -> Color.Red
        CharacterStatus.UNKNOWN -> Color.Gray
    }