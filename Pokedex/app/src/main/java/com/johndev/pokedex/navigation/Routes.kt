package com.johndev.pokedex.navigation

sealed class Routes(val route: String) {

    data object PokedexScreen : Routes("PokedexScreen")
    data object DetailsScreen : Routes("DetailsScreen")

}