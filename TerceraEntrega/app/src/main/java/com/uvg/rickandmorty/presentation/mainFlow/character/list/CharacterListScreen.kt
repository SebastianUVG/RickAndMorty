package com.uvg.rickandmorty.presentation.mainFlow.character.list

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.rickandmorty.data.model.Character
import com.uvg.rickandmorty.data.source.CharacterDb
import com.uvg.rickandmorty.presentation.ui.theme.RickAndMortyTheme


@Composable
fun CharacterListRoute(
    onCharacterClick: (Int) -> Unit,
    viewModel: CharacterListViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // Llama automáticamente a la función para obtener personajes si no hay error y no hay datos
    if (!state.loading && state.data == null && !state.hasError) {
        viewModel.getCharacters()
    }

    CharacterListScreen(
        state = state,
        onCharacterClick = onCharacterClick,
        onRetryClick = { viewModel.retryLoading() },
        onErrorClick = { viewModel.triggerError() }
    )
}


@Composable
private fun CharacterListScreen(
    state: CharacterListState,
    onCharacterClick: (Int) -> Unit,
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
            Text(text = "No se encontraron personajes.") // Mensaje si no hay datos
        } else {
            LazyColumn(modifier = modifier) {
                items(state.data) { item ->
                    CharacterItem(
                        character = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onCharacterClick(item.id) }
                    )
                }
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
            Text(text = "Cargando personajes, por favor espera.")
        }
    }
}

@Composable
fun ErrorScreen(onRetryClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Ha ocurrido un error al cargar los personajes.")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onRetryClick) {
                Text(text = "Reintentar")
            }
        }
    }
}


@Composable
private fun CharacterItem(
    character: Character,
    modifier: Modifier = Modifier
) {
    val imageBackgroundColors = listOf(
        MaterialTheme.colorScheme.error,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.secondaryContainer,
        MaterialTheme.colorScheme.tertiaryContainer,
        MaterialTheme.colorScheme.inverseSurface
    )
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Surface(
            modifier = Modifier.size(48.dp),
            color = imageBackgroundColors[(character.id % (imageBackgroundColors.count() - 1))],
            shape = CircleShape
        ) {
            Box {
                Icon(
                    Icons.Outlined.Person, contentDescription = "Image",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        Column {
            Text(text = character.name)
            Text(
                text = "${character.species} * ${character.status}",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

