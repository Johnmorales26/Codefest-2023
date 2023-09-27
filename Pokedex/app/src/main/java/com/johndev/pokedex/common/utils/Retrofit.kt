package com.johndev.pokedex.common.utils

import com.johndev.pokedex.common.utils.Constants.BASE_URL
import com.johndev.pokedex.dataAccess.PokemonService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: PokemonService = retrofit.create(PokemonService::class.java)

}