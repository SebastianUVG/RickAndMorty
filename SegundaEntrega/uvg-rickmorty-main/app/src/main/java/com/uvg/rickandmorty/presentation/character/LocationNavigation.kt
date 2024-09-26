package com.uvg.rickandmorty.presentation.character

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.uvg.rickandmorty.presentation.character.list.LocationListDestination
import com.uvg.rickandmorty.presentation.character.list.locationListScreen
import com.uvg.rickandmorty.presentation.character.location.locationDetailScreen
import com.uvg.rickandmorty.presentation.character.location.navigateToLocationDetailScreen

import kotlinx.serialization.Serializable

@Serializable
data object LocationNavGraph

fun NavController.navigateToLocationGraph(navOptions: NavOptions? = null) {
    this.navigate(LocationNavGraph, navOptions)
}

fun NavGraphBuilder.locationGraph(
    navController: NavController
) {
    navigation<LocationNavGraph>(
        startDestination = LocationListDestination
    ) {
        locationListScreen(
            onLocationClick = navController::navigateToLocationDetailScreen
        )
        locationDetailScreen(
            onNavigateBack = navController::navigateUp
        )
    }
}