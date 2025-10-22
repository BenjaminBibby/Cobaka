package com.example.cobaka.Dogs.Mappers

import com.example.cobaka.Common.Interfaces.IMapper
import com.example.cobaka.Dogs.DTO.DogDTO
import com.example.cobaka.Dogs.Models.Dog
import javax.inject.Singleton

// Map from DogDTO to Dog(Model)
@Singleton
class DogMapper() : IMapper<DogDTO, List<Dog>> {

    override fun map(from: DogDTO): List<Dog> {

        val dogs = mutableListOf<Dog>()

        for(message in from.message){
            dogs.add(Dog(
                breed = extractAndFormatBreed(message),
                imageUrl = message
            ))
        }

        return dogs
    }

    private fun extractAndFormatBreed(url: String): String {
        return url
            .substringAfter("breeds/")
            .substringBefore('/')
            .split('-')
            .joinToString(" ") { it.replaceFirstChar(Char::titlecase) }
    }
}