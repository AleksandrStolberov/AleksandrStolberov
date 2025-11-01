package com.example.rickandmorty.domain

import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickandmorty.data.CharactersRepository
import com.example.rickandmorty.data.database.CharactersEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) {

    private val mapper: Mapper<CharactersEntity, Character> = CharacterMapper()

    fun invoke(): Flow<PagingData<Character>> =
        repository.getCharacters().map { pagingData ->
            pagingData.map { mapper.map(it) }
        }
}