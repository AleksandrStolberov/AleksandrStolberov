package com.example.data.di

import com.example.data.RandomUserRepositoryImpl
import com.example.data.networking.RandomUserApi
import com.example.domain.RandomUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun providesRandomUserRepository(api: RandomUserApi): RandomUserRepository {
        return RandomUserRepositoryImpl(api)
    }

}