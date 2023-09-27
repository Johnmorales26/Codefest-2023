package com.johndev.pokedex.common.entities

data class PokemonEntity(
    var id: Int,
    var name: String,
    var types: List<Type>,
    var sprites: Sprites,
)