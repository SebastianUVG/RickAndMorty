package com.uvg.rickandmorty.presentation.login

import androidx.datastore.core.DataStore
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import java.util.prefs.Preferences


@Serializable
data object LoginDestination

fun NavGraphBuilder.loginScreen(
    onLoginClick: () -> Unit
) {
    composable<LoginDestination> {
        LoginRoute(
            onLoginClick = onLoginClick,
        )
    }
}

