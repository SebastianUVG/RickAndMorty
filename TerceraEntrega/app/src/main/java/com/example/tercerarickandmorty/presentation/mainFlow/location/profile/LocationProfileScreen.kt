package com.uvg.rickandmorty.presentation.mainFlow.location.profile

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.rickandmorty.data.model.Location
import com.uvg.rickandmorty.presentation.ui.theme.RickAndMortyTheme


@Composable
fun LocationProfileRoute(
    onNavigateBack: () -> Unit,
    viewModel: LocationProfileViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // Llama automáticamente a la función para obtener información
    // Solo llama a getLocationData si no hay error y no hay datos
    if (!state.loading && state.data == null && !state.hasError) {
        viewModel.getLocationData()
    }

    LocationProfileScreen(
        state = state,
        onNavigateBack = onNavigateBack,
        onRetryClick = {
            viewModel.retryLoading() // Reintentar obtener datos
        },
        onErrorClick = {
            viewModel.triggerError() // Cambia a estado de error al hacer clic en la pantalla de carga
        }
    )
}








@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocationProfileScreen(
    state: LocationProfileState,
    onRetryClick: () -> Unit,
    onErrorClick: () -> Unit, // Nuevo parámetro
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        TopAppBar(
            title = {
                Text("Location Details")
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        )
        LocationProfileContent(
            location = state.data,
            isLoading = state.loading,
            hasError = state.hasError,
            onRetryClick = onRetryClick,
            onErrorClick = onErrorClick, // Pasar el nuevo parámetro
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun LocationProfileContent(
    location: Location?,
    isLoading: Boolean,
    hasError: Boolean,
    onRetryClick: () -> Unit,
    onErrorClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        if (isLoading) {
            // Llama a triggerError cuando se hace clic en la pantalla de carga
            LoadingScreen(onClick = onErrorClick)
        } else if (hasError) {
            // Si hay un error, muestra la pantalla de error
            ErrorScreen(onRetryClick = onRetryClick)

        } else if (location == null) {
            // Si no hay datos, mostrar un mensaje apropiado
            Text(text = "No se encontraron datos de ubicación.")
        } else {
            // Muestra el contenido de la ubicación
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 64.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = location.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                LocationProfilePropItem(
                    title = "ID:",
                    value = location.id.toString(),
                    modifier = Modifier.fillMaxWidth()
                )
                LocationProfilePropItem(
                    title = "Type:",
                    value = location.type,
                    modifier = Modifier.fillMaxWidth()
                )
                LocationProfilePropItem(
                    title = "Dimensions:",
                    value = location.dimension,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}



@Composable
fun LoadingScreen(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() }, // Cambia el estado a error al hacer clic
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Cargando, paciente.")
        }
    }
}




@Composable
private fun LocationProfilePropItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title)
        Text(text = value)
    }
}





@Composable
fun ErrorScreen(onRetryClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Ha ocurrido un error.")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onRetryClick) {
                Text(text = "Reintentar")
            }
        }
    }
}



@Preview
@Composable
private fun PreviewLocationProfileScreen() {
    RickAndMortyTheme {
        Surface {
            LocationProfileScreen(
                state = LocationProfileState(
                    loading = false,
                    hasError = false,
                    data = Location(1, "Earth (C-137)", "Planet", "Dimension C-137")
                ),
                onNavigateBack = { },
                onRetryClick = { }, // Parámetro onRetryClick
                onErrorClick = { }, // Parámetro onErrorClick
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLoadingLocationProfileScreen() {
    RickAndMortyTheme {
        Surface {
            LocationProfileScreen(
                state = LocationProfileState(
                    loading = true,
                    hasError = false, // No hay error, está cargando
                    data = null
                ),
                onNavigateBack = { },
                onRetryClick = { }, // Parámetro onRetryClick
                onErrorClick = { }, // Parámetro onErrorClick
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview
@Composable
private fun PreviewEmptyLocationProfileScreen() {
    RickAndMortyTheme {
        Surface {
            LocationProfileScreen(
                state = LocationProfileState(
                    loading = false,
                    hasError = false, // No hay error, pero no hay datos
                    data = null
                ),
                onNavigateBack = { },
                onRetryClick = { }, // Parámetro onRetryClick
                onErrorClick = { }, // Parámetro onErrorClick
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview
@Composable
private fun PreviewErrorLocationProfileScreen() {
    RickAndMortyTheme {
        Surface {
            LocationProfileScreen(
                state = LocationProfileState(
                    loading = false,
                    hasError = true, // Simulación de un estado de error
                    data = null
                ),
                onNavigateBack = { },
                onRetryClick = { }, // Parámetro onRetryClick
                onErrorClick = { }, // Parámetro onErrorClick
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


