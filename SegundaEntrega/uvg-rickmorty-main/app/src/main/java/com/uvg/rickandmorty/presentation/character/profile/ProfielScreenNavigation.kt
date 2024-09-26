package com.uvg.rickandmorty.presentation.character.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.uvg.rickandmorty.presentation.character.CharacterNavGraph
import com.uvg.rickandmorty.presentation.login.LoginDestination
import com.uvg.rickandmorty.presentation.login.LoginRoute

import kotlinx.serialization.Serializable
import java.lang.reflect.Modifier


@Serializable
data object ProfileDestinationNavGraph

data object ProfileDestination {
    val route = "profile"
}

fun NavController.navigateToprofileScreen(navOptions: NavOptions? = null) {
    this.navigate(CharacterNavGraph, navOptions)
}

fun NavGraphBuilder.profileScreen(
    onLoginClick: () -> Unit
) {
    composable<ProfileDestination> {
        LoginRoute(
            onLoginClick = onLoginClick,
        )
    }
}