package com.example.composeapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composeapp.data.database.BinDatabase.Companion.DB_VERSION
import com.example.composeapp.data.model.Card
import dagger.Module
import dagger.Provides

@Database(entities = [Card::class], version = DB_VERSION)
abstract class BinDatabase : RoomDatabase() {

    abstract fun binDao(): BinDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "bin_database"
    }

}