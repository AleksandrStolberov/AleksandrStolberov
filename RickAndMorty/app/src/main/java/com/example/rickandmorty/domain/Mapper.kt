package com.example.rickandmorty.domain

abstract class Mapper<From, To> {
    abstract suspend fun map(from: From): To
}