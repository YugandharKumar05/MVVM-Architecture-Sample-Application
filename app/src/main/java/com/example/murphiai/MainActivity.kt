package com.example.murphiai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.murphiai.data.network.ApiService
import com.example.murphiai.data.repository.CharacterRepository
import com.example.murphiai.ui.theme.screen.CharacterListScreen
import com.example.murphiai.ui.theme.viewmodel.CharacterViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        val repository = CharacterRepository(apiService)
        val viewModel = CharacterViewModel(repository)

        setContent {
            CharacterListScreen(viewModel = viewModel)
        }

        viewModel.getCharacters()
    }
}