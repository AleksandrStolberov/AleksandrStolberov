package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.AppDatabase
import com.example.data.database.CoursesDao
import com.example.data.database.DatabaseInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DatabaseInfo.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCoursesDao(database: AppDatabase): CoursesDao {
        return database.coursesDao()
    }


}