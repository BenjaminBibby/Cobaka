package com.example.cobaka.Dogs.Repositories

import com.example.cobaka.Common.Interfaces.IMapper
import com.example.cobaka.Dogs.DTO.DogDTO
import com.example.cobaka.Dogs.Mappers.DogMapper
import com.example.cobaka.Dogs.Models.Dog
import com.example.cobaka.Dogs.Services.DogService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogRepository @Inject constructor(
    private val dogService: DogService,
    private val dogMapper: DogMapper
//    private val dogMapper: IMapper<DogDTO, List<Dog>>
) {
    suspend fun getRandomDogs(count: Int = 10): Result<List<Dog>> {
        val serviceResult = dogService.getRandomDogs(count)

        return serviceResult.map { dogDto ->
            // If the result is a success, this block runs.
            // 'dogDto' is the unwrapped DogDTO.
            // Now you can map it to your domain model.
            dogMapper.map(dogDto)
        }
    }
}