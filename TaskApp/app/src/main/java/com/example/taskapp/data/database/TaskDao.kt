package com.example.taskapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    suspend fun addTask(task: TaskDbo)

    @Query("SELECT * FROM task_db")
    fun getTasks(): Flow<List<TaskDbo>>

    @Update
    suspend fun updateStatus(task: TaskDbo)

    @Delete
    suspend fun deleteTask(task: TaskDbo)

    @Query("SELECT date FROM task_db")
    fun allDates(): Flow<List<Long>>

}