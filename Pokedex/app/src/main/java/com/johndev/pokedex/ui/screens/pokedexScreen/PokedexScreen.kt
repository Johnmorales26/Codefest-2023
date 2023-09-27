package com.johndev.pokedex.ui.screens.pokedexScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.johndev.pokedex.R
import com.johndev.pokedex.common.entities.PokemonEntity
import com.johndev.pokedex.common.entities.Sprites
import com.johndev.pokedex.common.entities.Type
import com.johndev.pokedex.common.utils.Retrofit.service
import com.johndev.pokedex.common.utils.getImageById
import com.johndev.pokedex.ui.screens.pokedexScreen.components.PokemonCard
import com.johndev.pokedex.ui.screens.pokedexScreen.components.PokemonCardLoad
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokedexScreen() {
    val pokemonListState = remember { mutableStateOf<List<PokemonEntity>?>(null) }
    val offset = remember { mutableStateOf(0) }
    val limit = remember { mutableStateOf(10) }

    // Lista temporal para acumular los Pokémon
    val tempList = mutableListOf<PokemonEntity>()

    // Realizar la solicitud de los 10 primeros Pokémon una vez que se inicia la pantalla
    for (index in offset.value..limit.value) {
        LaunchedEffect(index) {
            try {
                val response = service.getPokemonInfo(index.toString())
                tempList.add(response) // Agrega el Pokémon a la lista temporal
                // Verifica si hemos recopilado todos los Pokémon
                if (tempList.size == 10) {
                    // Asigna la lista completa a pokemonListState una vez que se recopilan todos los Pokémon
                    pokemonListState.value = tempList.toList().sortedByDescending { index }
                }
            } catch (e: Exception) {
                // Manejar errores de solicitud aquí
                Log.e("PokedexScreen", "Error: ${e.message}")
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                AppHeader()
            },
            content = { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    if (pokemonListState.value != null) {
                        PokemonList(pokemonListState.value!!) {
                            pokemonListState.value = null
                            offset.value += 10
                            limit.value += 10
                        }
                    } else {
                        // Puedes mostrar un indicador de carga o mensaje de error aquí mientras se carga la información.
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            for (index in offset.value..limit.value) {
                                PokemonCardLoad(Color(0xffe5e9ec))
                            }
                        }
                    }
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
private fun PokemonList(pokemonList: List<PokemonEntity>, callback: () -> Unit) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val pokemonTemp = pokemonList.sortedBy { pokemon -> pokemon.id }
        pokemonTemp.forEach {
            PokemonCard(pokemon = it)
            Spacer(modifier = Modifier.height(4.dp))
        }
        Button(onClick = {
            callback()
            runBlocking { // Scroll hacia el principio (parte superior)
                scrollState.scrollTo(0)
            }
        }) {
            Text(text = "Load more")
        }
    }
}