package com.example.data

import android.util.Log
import com.example.data.networking.RandomUserApi
import com.example.domain.RandomUserRepository
import com.example.domain.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RandomUserRepositoryImpl @Inject constructor(val api: RandomUserApi) : RandomUserRepository {

    override suspend fun getRandomUser(gender: String, nat: String): List<User> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getRandomUser(gender, nat)
                response.results.map {
                    it.toDomain()
                }

            } catch (e: Exception) {
                Log.e("ApiError", e.message.toString())
                emptyList()
            }
        }
    }

}