package com.example.rickandmorty.domain

import com.example.rickandmorty.data.database.CharactersEntity

class CharacterMapper : Mapper<CharactersEntity, Character>() {
    override suspend fun map(from: CharactersEntity) = from.run {
        Character(
            name = name,
            gender = gender,
            type = type,
            status = getCharacterStatusEnum(status),
            image = image
        )
    }

}