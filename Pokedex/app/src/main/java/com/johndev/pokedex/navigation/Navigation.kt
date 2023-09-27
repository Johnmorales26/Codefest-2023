package com.johndev.pokedex.navigation

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.johndev.pokedex.ui.screens.detailScreen.DetailsScreen
import com.johndev.pokedex.ui.screens.pokedexScreen.PokedexScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.PokedexScreen.route,
    ) {
        composable(Routes.PokedexScreen.route) {
            PokedexScreen()
        }
        composable(Routes.DetailsScreen.route) {
            DetailsScreen()
        }
    }
}