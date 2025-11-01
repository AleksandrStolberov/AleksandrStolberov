package com.example.rickandmorty.data.remote

import com.example.rickandmorty.data.database.CharactersEntity
import com.example.rickandmorty.data.model.CharacterResponse
import com.example.rickandmorty.domain.Mapper
import javax.inject.Inject

class EntityMapper : Mapper<CharacterResponse, CharactersEntity>() {
    override suspend fun map(from: CharacterResponse) = from.run {
        CharactersEntity(
            id = id,
            name = name,
            gender = gender,
            status = status,
            type = type,
            image = image
        )
    }
}