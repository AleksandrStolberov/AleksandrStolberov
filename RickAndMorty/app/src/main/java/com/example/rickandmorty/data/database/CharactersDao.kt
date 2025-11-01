package com.example.rickandmorty.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CharactersDao {

    @Query("SELECT * FROM characters")
    fun getPagedItems(): PagingSource<Int, CharactersEntity>

    @Insert
    suspend fun insertAll(items: List<CharactersEntity>)

    @Query("DELETE FROM characters")
    suspend fun clearAll()
}