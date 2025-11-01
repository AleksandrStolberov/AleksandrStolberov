package com.example.domain

abstract class Mapper<From, To> {
    abstract suspend fun map(from: From): To
}