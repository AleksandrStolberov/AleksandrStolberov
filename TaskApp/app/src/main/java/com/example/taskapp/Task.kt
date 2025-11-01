package com.example.taskapp

import java.util.Date

data class Task(
    val id: Int,
    val status: Status,
    val theme: String,
    val text: String,
    val date: Date,
    val deadline: Date,
    val conclusion: String?
)



