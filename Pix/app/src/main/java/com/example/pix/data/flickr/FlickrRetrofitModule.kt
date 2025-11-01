package com.example.pix.data.flickr

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FlickrRetrofitModule {

    @Singleton
    @Provides
    fun baseUrl(): String {
        return BASE_URL
    }


    @Singleton
    @Provides
    fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Singleton
    @Provides
    fun providesRepository(api: FlickrApi) = FlickrRepository(api)


    @Singleton
    @Provides
    fun api(): FlickrApi {
        return retrofit().create()
    }

    private const val BASE_URL = "https://www.flickr.com"

}