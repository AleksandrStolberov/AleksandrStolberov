package com.example.rickandmorty.di

import com.example.rickandmorty.data.CharactersRepository
import com.example.rickandmorty.data.database.CharactersDao
import com.example.rickandmorty.data.remote.CharactersRemoteMediator
import com.example.rickandmorty.data.remote.CharactersRemoteMediatorFactory
import com.example.rickandmorty.data.remote.RickAndMortyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(
        api: RickAndMortyApi,
        dao: CharactersDao,
        mediatorFactory: CharactersRemoteMediatorFactory
    ): CharactersRepository {
        return CharactersRepository(api, dao, mediatorFactory)
    }

}