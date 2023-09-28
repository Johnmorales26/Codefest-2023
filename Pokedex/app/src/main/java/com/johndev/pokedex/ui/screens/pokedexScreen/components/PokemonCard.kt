package com.johndev.pokedex.ui.screens.pokedexScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.johndev.pokedex.common.entities.PokemonEntity
import com.johndev.pokedex.common.entities.Sprites
import com.johndev.pokedex.common.entities.Type
import com.johndev.pokedex.common.entities.Types
import com.johndev.pokedex.common.utils.capitalizeFirstLetter
import com.johndev.pokedex.common.utils.formatId
import com.johndev.pokedex.common.utils.getImageById
import com.johndev.pokedex.ui.theme.PokedexTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonCard(pokemon: PokemonEntity) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .border(width = 2.dp, color = Color(0xffF5F5F5))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = formatId(pokemon.id),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    Text(
                        text = pokemon.name.capitalizeFirstLetter(),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                LazyRow(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(pokemon.types.size) { index ->
                        AssistChip(
                            onClick = { },
                            label = { Text(text = pokemon.types[index].type.name.capitalizeFirstLetter()) }
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            Color(0xffF0F0F0),
                            shape = RoundedCornerShape(
                                topStart = 64.dp,
                                bottomStart = 64.dp,
                                topEnd = 16.dp,
                                bottomEnd = 16.dp
                            )
                        )
                ) {
                    AsyncImage(
                        model = pokemon.sprites.front_default,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokedexTheme {
        val pokemon = PokemonEntity(
            id = 1,
            name = "bulbasaur",
            types = listOf(
                Types(
                    slot = 1,
                    type = Type(
                        name = "grass",
                        url = ""
                    )
                ),
                Types(
                    slot = 2,
                    type = Type(
                        name = "poison",
                        url = ""
                    )
                )
            ),
            sprites = Sprites(
                front_default = getImageById(1)
            )
        )
        PokemonCard(pokemon)
    }
}