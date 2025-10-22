package com.example.cobaka.Dogs.DTO

import kotlinx.serialization.Serializable

// TODO: Rename DogResponse for clarity?

@Serializable
class DogDTO (
    val message: List<String>,
    val status: String
)