package com.example.taskapp.data.mapper

import com.example.taskapp.Status
import com.example.taskapp.Task
import com.example.taskapp.data.database.TaskDbo
import java.util.Date

class TaskMapper : Mapper<TaskDbo, Task>() {
    override suspend fun map(from: TaskDbo): Task = from.run {
        Task(
            id = id,
            status = Status.valueOf(status),
            theme = theme,
            text = text,
            date = Date(date),
            deadline = Date(deadline),
            conclusion = conclusion
        )
    }
}