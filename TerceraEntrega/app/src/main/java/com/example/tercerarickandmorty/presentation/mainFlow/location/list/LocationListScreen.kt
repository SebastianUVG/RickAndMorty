package com.uvg.rickandmorty.presentation.mainFlow.location.list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.rickandmorty.data.model.Location
import com.uvg.rickandmorty.data.source.LocationDb
import com.uvg.rickandmorty.presentation.mainFlow.character.list.ErrorScreen
import com.uvg.rickandmorty.presentation.mainFlow.character.list.LoadingScreen
import com.uvg.rickandmorty.presentation.ui.theme.RickAndMortyTheme

@Composable
fun LocationListRoute(
    onLocationClick: (Int) -> Unit,
    viewModel: LocationListViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // Llama automáticamente a la función para obtener localizaciones si no hay error y no hay datos
    if (!state.loading && state.data == null && !state.hasError) {
        viewModel.getLocations()
    }

    LocationListScreen(
        state = state,
        onLocationClick = onLocationClick,
        onRetryClick = { viewModel.retryLoading() },
        onErrorClick = { viewModel.triggerError() }
    )
}

@Composable
private fun LocationListScreen(
    state: LocationListState,
    onLocationClick: (Int) -> Unit,
    onRetryClick: () -> Unit,
    onErrorClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        if (state.loading) {
            LoadingScreen(onClick = onErrorClick) // Pantalla de carga
        } else if (state.hasError) {
            ErrorScreen(onRetryClick = onRetryClick) // Pantalla de error
        } else if (state.data.isNullOrEmpty()) {
            Text(text = "No se encontraron localizaciones.") // Mensaje si no hay datos
        } else {
            LazyColumn(modifier = modifier) {
                items(state.data) { item ->
                    LocationItem(
                        location = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onLocationClick(item.id) }
                    )
                }
            }
        }
    }
}


@Composable
private fun LocationItem(
    location: Location,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .padding(16.dp)
    ) {
        Text(text = location.name)
        Text(
            text = location.type,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

