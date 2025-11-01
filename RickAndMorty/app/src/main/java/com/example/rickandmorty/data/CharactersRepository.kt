package com.example.rickandmorty.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.database.CharactersDao
import com.example.rickandmorty.data.database.CharactersEntity
import com.example.rickandmorty.data.model.CharacterResponse
import com.example.rickandmorty.data.remote.CharactersRemoteMediator
import com.example.rickandmorty.data.remote.CharactersRemoteMediatorFactory
import com.example.rickandmorty.data.remote.RickAndMortyApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val api: RickAndMortyApi,
    private val dao: CharactersDao,
    private val mediatorFactory: CharactersRemoteMediatorFactory
){

    @OptIn(ExperimentalPagingApi::class)
    fun getCharacters(): Flow<PagingData<CharactersEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 40
            ),
            remoteMediator = mediatorFactory.create()
        ) {
            dao.getPagedItems()
        }.flow
    }

    suspend fun getCharactersByName(name: String): List<CharacterResponse> {
        return try {
            val response = api.getCharacterByName(name)
            response.results
        } catch (e: Exception) {
            Log.d("Searched", e.message.toString())
            emptyList()
        }
    }

}