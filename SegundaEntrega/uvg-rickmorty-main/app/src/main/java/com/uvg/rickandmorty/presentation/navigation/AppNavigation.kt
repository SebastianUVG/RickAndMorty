package com.uvg.rickandmorty.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.uvg.rickandmorty.presentation.character.CharacterNavGraph
import com.uvg.rickandmorty.presentation.character.characterGraph
import com.uvg.rickandmorty.presentation.character.navigateToCharacterGraph
import com.uvg.rickandmorty.presentation.login.LoginDestination
import com.uvg.rickandmorty.presentation.login.loginScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = LoginDestination,
        modifier = modifier
    ) {
        loginScreen(
            onLoginClick = {
                navController.navigateToCharacterGraph(
                    navOptions = NavOptions.Builder().setPopUpTo<LoginDestination>(
                        inclusive = true
                    ).build()
                )
            }
        )
        characterGraph(navController)
    }
}