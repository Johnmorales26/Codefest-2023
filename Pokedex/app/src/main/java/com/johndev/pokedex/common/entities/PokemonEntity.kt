package com.johndev.pokedex.common.entities

data class PokemonEntity(
    var id: Int = 0,
    var name: String = "",
    val types: List<Types>,
    var sprites: Sprites = Sprites(""),
)