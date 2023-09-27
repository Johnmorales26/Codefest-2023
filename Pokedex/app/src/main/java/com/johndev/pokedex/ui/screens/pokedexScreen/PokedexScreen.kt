package com.johndev.pokedex.ui.screens.pokedexScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.johndev.pokedex.R
import com.johndev.pokedex.common.entities.PokemonEntity
import com.johndev.pokedex.common.entities.Sprites
import com.johndev.pokedex.common.entities.Type
import com.johndev.pokedex.common.utils.getImageById
import com.johndev.pokedex.ui.screens.pokedexScreen.components.PokemonCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokedexScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                AppHeader()
            },
            content = { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    PokemonListBody()
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppHeader() {
    CenterAlignedTopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
}

@Composable
private fun PokemonListBody() {
    val pokemonList by remember { mutableStateOf(pokemonFakeList) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(pokemonList.size) { index ->
            PokemonCard(pokemon = pokemonList[index])
        }
    }
}


val pokemonFakeList = listOf(
    PokemonEntity(
        id = 1,
        name = "Bulbasaur",
        types = listOf(
            Type(name = "Grass"),
            Type(name = "Poison")
        ),
        sprites = Sprites(
            front_default = getImageById(1)
        )
    ),
    PokemonEntity(
        id = 4,
        name = "Charmander",
        types = listOf(
            Type(name = "Fire")
        ),
        sprites = Sprites(
            front_default = getImageById(4)
        )
    ),
    PokemonEntity(
        id = 7,
        name = "Squirtle",
        types = listOf(
            Type(name = "Water")
        ),
        sprites = Sprites(
            front_default = getImageById(7)
        )
    ),
)
