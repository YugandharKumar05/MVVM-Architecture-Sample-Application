package com.example.murphiai.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.murphiai.data.model.Character
import com.example.murphiai.data.repository.CharacterRepository
import com.example.murphiai.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterViewModel(private val repository: CharacterRepository) : ViewModel() {

    private val _characters = MutableStateFlow<NetworkResult<List<Character>>>(NetworkResult.Loading())
    val characters: StateFlow<NetworkResult<List<Character>>> = _characters

    init {
        getCharacters() // ✅ Load data when ViewModel is created
    }

    fun getCharacters() {
        viewModelScope.launch {
            _characters.value = repository.getCharacters()
        }
    }
}
