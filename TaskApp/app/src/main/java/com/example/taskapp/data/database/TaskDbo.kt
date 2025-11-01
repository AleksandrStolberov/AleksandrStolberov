package com.example.taskapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_db")
data class TaskDbo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val status: String,
    val theme: String,
    val text: String,
    val date: Long,
    val deadline: Long,
    val conclusion: String?
)