package com.example.data.di

import com.example.data.network.CoursesApi
import com.example.data.CoursesRepositoryImpl
import com.example.data.database.CoursesDao
import com.example.domain.CoursesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideCoursesRepository(api: CoursesApi, dao: CoursesDao): CoursesRepository {
        return CoursesRepositoryImpl(api, dao)
    }

}