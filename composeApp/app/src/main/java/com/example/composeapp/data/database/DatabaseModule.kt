package com.example.composeapp.data.database

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun providesDatabase(application: Application): BinDatabase {
        return Room.databaseBuilder(
            application,
            BinDatabase::class.java,
            BinDatabase.DB_NAME
        )
            .build()
    }

    @Provides
    fun providesBinDao(db: BinDatabase): BinDao {
        return db.binDao()
    }
}