package com.example.composeapp.data.model

abstract class Mapper<From, To> {
    abstract suspend fun map(from: From): To
}