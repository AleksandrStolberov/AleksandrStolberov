package com.example.rickandmorty.domain

import android.util.Log
import com.example.rickandmorty.data.CharactersRepository
import com.example.rickandmorty.data.model.CharacterResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCharactersByNameUseCase @Inject constructor(private val repository: CharactersRepository) {

    private val mapper: Mapper<CharacterResponse, Character> = CharacterNameMapper()

    suspend fun invoke(name: String): List<Character> =
        repository.getCharactersByName(name).map { items ->
            Log.d("Searched", items.name)
            mapper.map(items)
        }
}