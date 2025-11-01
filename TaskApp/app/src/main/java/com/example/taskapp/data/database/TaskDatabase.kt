package com.example.taskapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskapp.Task
import com.example.taskapp.data.database.TaskDatabase.Companion.DB_VERSION

@Database(entities = [TaskDbo::class], version = DB_VERSION)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "task_database"
    }

}