package com.example.composeapp.data.networking

import com.example.composeapp.data.BinRepository
import com.example.composeapp.data.database.BinDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Singleton
    @Provides
    fun baseUrl(): String {
        return BASE_URL
    }

    @Singleton
    @Provides
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun binApi(): BinApi {
        return retrofit().create()
    }

    @Singleton
    @Provides
    fun providesRepository(binApi: BinApi, binDao: BinDao) = BinRepository(binApi, binDao)

    private const val BASE_URL = "https://lookup.binlist.net"
}