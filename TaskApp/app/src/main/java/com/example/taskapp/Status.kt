package com.example.taskapp

import androidx.annotation.DrawableRes

enum class Status {

    NEW,
    PROGRESS,
    FINISHED,
    CANCELED;

    fun statusName(): String {
        return when(this) {
            NEW -> "Новое"
            PROGRESS -> "В работе"
            FINISHED -> "Завершено"
            CANCELED -> "Отменено"
        }
    }

    @DrawableRes
    fun icon(): Int {
        return when(this) {
            NEW -> R.drawable.new_task
            PROGRESS -> R.drawable.progress
            FINISHED -> R.drawable.finished
            CANCELED -> R.drawable.cancel
        }
    }

}