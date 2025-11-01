package com.example.taskapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.data.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = TaskRepository()

    val allTasks = repository.getAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(10000L),
        initialValue = emptyList()
    )

    fun addTask(task: Task) {
        viewModelScope.launch {
            repository.addTask(task)
        }

    }

    val allDates = repository.allDates().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(10000L),
        initialValue = emptyList()
    )

    fun updateStatus(task: Task, newStatus: Status, comment: String?) {
        val updatedTask = task.copy(status = newStatus, conclusion = comment)
        Log.d("TaskList", task.toString())
        Log.d("TaskList", updatedTask.toString())
        viewModelScope.launch {
            repository.updateStatus(updatedTask)
        }

    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

}