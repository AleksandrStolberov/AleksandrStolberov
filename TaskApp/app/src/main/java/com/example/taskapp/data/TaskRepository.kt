package com.example.taskapp.data

import android.util.Log
import com.example.taskapp.Task
import com.example.taskapp.data.database.Database
import com.example.taskapp.data.database.TaskDbo
import com.example.taskapp.data.mapper.Mapper
import com.example.taskapp.data.mapper.TaskDboMapper
import com.example.taskapp.data.mapper.TaskMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date

class TaskRepository {

    private val taskDao = Database.instance.taskDao()
    private val mapper: Mapper<TaskDbo, Task> = TaskMapper()
    private val mapperDbo: Mapper<Task, TaskDbo> = TaskDboMapper()

    fun getAll(): Flow<List<Task>> {
        return taskDao.getTasks().map { list ->
            Log.d("TaskList", list.toString())
            list.map {
                mapper.map(it)
            }
        }
    }

    fun allDates(): Flow<List<Date>> {
         return taskDao.allDates().map {
             it.map { date ->
                 Date(date)
             }
         }
    }

    suspend fun addTask(task: Task) {
        taskDao.addTask(mapperDbo.map(task))
    }

    suspend fun updateStatus(task: Task) {
        val t = mapperDbo.map(task)
        taskDao.updateStatus(t)
    }

    suspend fun deleteTask(task: Task) {
        val t = mapperDbo.map(task)
        taskDao.deleteTask(t)
    }
}