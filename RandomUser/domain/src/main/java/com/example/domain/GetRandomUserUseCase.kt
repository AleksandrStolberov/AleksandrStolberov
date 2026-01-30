package com.example.domain

import javax.inject.Inject

class GetRandomUserUseCase @Inject constructor(val repository: RandomUserRepository) {

    suspend fun getRandomUser(gender: String, nat: String): List<User> {
        return repository.getRandomUser(gender, nat)
    }

}