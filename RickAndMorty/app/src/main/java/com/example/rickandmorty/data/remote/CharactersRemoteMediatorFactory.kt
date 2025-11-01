package com.example.rickandmorty.data.remote

import dagger.assisted.AssistedFactory


@AssistedFactory
interface CharactersRemoteMediatorFactory {
    fun create(): CharactersRemoteMediator
}
