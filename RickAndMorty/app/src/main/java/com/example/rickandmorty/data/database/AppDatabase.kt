package com.example.rickandmorty.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CharactersEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao

    companion object {
        const val DATABASE_NAME = "app_database"
    }
}