package com.example.domain

interface RandomUserRepository {

    suspend fun getRandomUser(gender: String, nat: String): List<User>

}