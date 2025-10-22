package com.example.cobaka.Dogs.Services

import com.example.cobaka.Dogs.DTO.DogDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogService @Inject constructor(
    private val httpClient : HttpClient
) {
    suspend fun getRandomDogs(count: Int) : Result<DogDTO> {
        val url = "https://dog.ceo/api/breeds/image/random/$count"
        println("URL: $url")
        return try {
                val response = httpClient.get(url)
            println(response)

            Result.success(response.body())
        } catch (e: Exception) {
            println("Error: $e")
            Result.failure(e)
        }
    }
}