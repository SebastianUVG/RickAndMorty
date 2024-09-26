package com.uvg.rickandmorty.presentation.navigation

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import com.uvg.rickandmorty.presentation.ui.theme.RickAndMortyTheme

@Composable
fun BottomNavBar(selectedItem: Int, onItemSelected: (Int) -> Unit) {

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface, // Color de fondo de la barra
        contentColor = MaterialTheme.colorScheme.onSurface // Color del contenido (íconos y texto)
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.People, contentDescription = "Characters") },
            label = { Text("Characters") },
            selected = selectedItem == 0,
            onClick = { onItemSelected(0) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.LocationOn, contentDescription = "Location") },
            label = { Text("Location") },
            selected = selectedItem == 1,
            onClick = { onItemSelected(1) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = selectedItem == 2,
            onClick = { onItemSelected(2) }
        )
    }

}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLoginScreen() {
    RickAndMortyTheme {
        Surface {
            BottomNavBar(selectedItem = 0, onItemSelected = {})
        }
    }
}