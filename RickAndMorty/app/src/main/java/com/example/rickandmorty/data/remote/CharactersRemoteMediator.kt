package com.example.rickandmorty.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.rickandmorty.data.database.CharactersDao
import com.example.rickandmorty.data.database.CharactersEntity
import dagger.assisted.AssistedInject

private var currentPage = 1

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator @AssistedInject constructor(
    private val api: RickAndMortyApi,
    private val dao: CharactersDao
) : RemoteMediator<Int, CharactersEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharactersEntity>
    ): MediatorResult {
        return try {
            val page = when(loadType) {
                LoadType.REFRESH -> {
                    Log.d("Characters", "REFRESH")
                    currentPage = 1
                    1
                }
                LoadType.PREPEND -> {
                    Log.d("Characters", "Prepend")
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    Log.d("Characters", "Append")
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null)
                        return MediatorResult.Success(endOfPaginationReached = true)
                    currentPage++
                    currentPage
                }
            }
            val mapper = EntityMapper()
            val response = api.getCharacters(page)

            if (loadType == LoadType.REFRESH) {
                dao.clearAll()
            }

            Log.d("Characters", "${response.info.pages}")
            val items = response.results.map { mapper.map(it) }

            dao.insertAll(items)
            Log.d("Characters", items.toString())


            val endOfPaginationReached = items.isEmpty() || page >= response.info.pages
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (e: Exception) {
            Log.d("Characters", "${e.message}")
            MediatorResult.Error(e)
        }
    }
}