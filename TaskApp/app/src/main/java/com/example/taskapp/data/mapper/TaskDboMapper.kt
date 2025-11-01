package com.example.taskapp.data.mapper

import com.example.taskapp.Task
import com.example.taskapp.data.database.TaskDbo

class TaskDboMapper : Mapper<Task, TaskDbo>() {
    override suspend fun map(from: Task): TaskDbo = from.run {
        TaskDbo(
            id = id,
            status = status.name,
            theme = theme,
            text = text,
            date = date.time,
            deadline = deadline.time,
            conclusion = conclusion
        )
    }
}