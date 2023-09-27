package com.johndev.pokedex.common.entities

data class PokemonEntity(
    var id: Int = 0,
    var name: String = "",
    var types: List<Type> = listOf(),
    var sprites: Sprites = Sprites(""),
)