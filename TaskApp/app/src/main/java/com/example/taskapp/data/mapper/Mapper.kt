package com.example.taskapp.data.mapper

abstract class Mapper<From, To> {
    abstract suspend fun map(from: From): To
}