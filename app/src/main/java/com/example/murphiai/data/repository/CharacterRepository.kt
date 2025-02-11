package com.example.murphiai.data.repository

import com.example.murphiai.data.model.Character
import com.example.murphiai.data.network.ApiService
import com.example.murphiai.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepository(private val apiService: ApiService) {

    suspend fun getCharacters(): NetworkResult<List<Character>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getCharacters()
                NetworkResult.Success(response.results)
            } catch (e: Exception) {
                NetworkResult.Error("Failed to fetch data")
            }
        }
    }
}
