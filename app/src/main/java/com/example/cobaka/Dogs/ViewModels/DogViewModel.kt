package com.example.cobaka.Dogs.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cobaka.Dogs.Models.Dog
import com.example.cobaka.Dogs.Repositories.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DogUiState(
    val dogs: List<Dog> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isLoadingMore: Boolean = false
)

@HiltViewModel
class DogViewModel @Inject constructor(
    private val dogRepository: DogRepository
) : ViewModel() {

    var uiState by mutableStateOf(DogUiState())
        private set

    init {
        loadDogs(initialLoad = true)
    }

    fun loadMoreDogs() {
        if (uiState.isLoadingMore) return
        loadDogs(initialLoad = false)
    }

    private fun loadDogs(initialLoad: Boolean) {
        viewModelScope.launch {
            uiState = if (initialLoad) {
                uiState.copy(isLoading = true)
            } else {
                uiState.copy(isLoadingMore = true)
            }

            val result = dogRepository.getRandomDogs(count = 20)

            result.onSuccess { dogs ->
                uiState = uiState.copy(
                    dogs = if (initialLoad) dogs else uiState.dogs + dogs,
                    isLoading = false,
                    isLoadingMore = false
                )

            }.onFailure { error ->
                uiState = uiState.copy(
                    error = error.message,
                    isLoading = false,
                    isLoadingMore = false
                )
            }

        }
    }
}