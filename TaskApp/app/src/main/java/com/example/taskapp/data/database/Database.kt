package com.example.taskapp.data.database

import android.content.Context
import androidx.room.Room


object Database {

    lateinit var instance: TaskDatabase
        private set

    fun init(context: Context) {
        instance = Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            TaskDatabase.DB_NAME
        ).build()
    }
}

