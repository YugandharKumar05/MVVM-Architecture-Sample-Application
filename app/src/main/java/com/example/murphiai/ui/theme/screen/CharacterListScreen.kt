package com.example.murphiai.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.murphiai.data.model.Character
import com.example.murphiai.ui.theme.viewmodel.CharacterViewModel
import com.example.murphiai.utils.NetworkResult

@Composable
fun CharacterListScreen(viewModel: CharacterViewModel) {
    val charactersState = viewModel.characters.collectAsState()

    when (val result = charactersState.value) {
        is NetworkResult.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is NetworkResult.Success -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(result.data) { character ->
                    CharacterItem(character)
                }
            }
        }
        is NetworkResult.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = result.message ?: "An error occurred", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
fun CharacterItem(character: Character) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(character.image),
                contentDescription = character.name,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(text = character.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = "Status: ${character.status}")
                Text(text = "Species: ${character.species}")
                Text(text = "Gender: ${character.gender}")
            }
        }
    }
}
