package com.example.courses.di

import com.example.domain.CoursesRepository
import com.example.domain.GetCoursesUseCase
import com.example.domain.GetFavoriteCoursesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGetCoursesUseCase(userRepository: CoursesRepository): GetCoursesUseCase {
        return GetCoursesUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideGetFavoriteCoursesUseCase(userRepository: CoursesRepository): GetFavoriteCoursesUseCase {
        return GetFavoriteCoursesUseCase(userRepository)
    }

}