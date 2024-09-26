    package com.uvg.rickandmorty.presentation.character
    
    import androidx.navigation.NavController
    import androidx.navigation.NavGraphBuilder
    import androidx.navigation.NavOptions
    import androidx.navigation.navigation
    import com.uvg.rickandmorty.presentation.character.list.CharacterListDestination
    import com.uvg.rickandmorty.presentation.character.list.characterListScreen
    import com.uvg.rickandmorty.presentation.character.profile.characterProfileScreen
    import com.uvg.rickandmorty.presentation.character.profile.navigateToCharacterProfileScreen
    import kotlinx.serialization.Serializable

    @Serializable
    data object CharacterNavGraph



    fun NavController.navigateToCharacterGraph(navOptions: NavOptions? = null) {
        this.navigate(CharacterNavGraph, navOptions)
    }

    fun NavGraphBuilder.characterGraph(
        navController: NavController
    ) {
        navigation<CharacterNavGraph>(
            startDestination = CharacterListDestination
        ) {
            characterListScreen(
                onCharacterClick = navController::navigateToCharacterProfileScreen
            )
            characterProfileScreen(
                onNavigateBack = navController::navigateUp
            )
        }
    }