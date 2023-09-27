package com.johndev.pokedex.dataAccess

import com.johndev.pokedex.common.entities.PokemonEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon/{idOrName}/")
    suspend fun getPokemonInfo(@Path("idOrName") idOrName: String): PokemonEntity

}