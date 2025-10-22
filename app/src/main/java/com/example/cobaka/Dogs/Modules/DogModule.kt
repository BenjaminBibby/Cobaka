package com.example.cobaka.Dogs.Modules

import com.example.cobaka.Common.Interfaces.IMapper
import com.example.cobaka.Dogs.DTO.DogDTO
import com.example.cobaka.Dogs.Mappers.DogMapper
import com.example.cobaka.Dogs.Models.Dog
import com.example.cobaka.Dogs.Repositories.DogRepository
import com.example.cobaka.Dogs.Services.DogService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DogModule {

    @Singleton
    @Provides
    fun provideDogService(httpClient: HttpClient) : DogService {
        println("Providing DogService")
        return DogService(httpClient)
    }

    @Singleton
    @Provides
//    fun provideDogMapper() : IMapper<DogDTO, List<Dog>> {
    fun provideDogMapper() : DogMapper {
        println("Providing DogMapper")
        return DogMapper()
    }

    @Singleton
    @Provides
    fun provideDogRepository(dogService: DogService, dogMapper: DogMapper) : DogRepository{
        println("Providing DogRepository")
        return DogRepository(dogService, dogMapper)
    }
}