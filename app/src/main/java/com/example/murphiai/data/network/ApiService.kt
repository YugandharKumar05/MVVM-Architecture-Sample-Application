package com.example.murphiai.data.network


import com.example.murphiai.data.model.Character
import retrofit2.http.GET

interface ApiService {
    @GET("character")
    suspend fun getCharacters(): CharacterResponse
}

data class CharacterResponse(val results: List<Character>)
