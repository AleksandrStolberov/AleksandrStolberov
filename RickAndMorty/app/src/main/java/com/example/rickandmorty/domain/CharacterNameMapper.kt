package com.example.rickandmorty.domain

import com.example.rickandmorty.data.model.CharacterResponse

class CharacterNameMapper : Mapper<CharacterResponse, Character>() {
    override suspend fun map(from: CharacterResponse) = from.run {
        Character(
            name = name,
            gender = gender,
            type = type,
            status = getCharacterStatusEnum(status),
            image = image
        )
    }
}