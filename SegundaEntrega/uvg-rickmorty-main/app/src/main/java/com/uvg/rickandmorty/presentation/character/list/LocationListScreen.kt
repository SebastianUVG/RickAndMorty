package com.uvg.rickandmorty.presentation.character.list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.rickandmorty.data.model.Location
import com.uvg.rickandmorty.data.source.LocationDb
import com.uvg.rickandmorty.presentation.navigation.BottomNavBar
import com.uvg.rickandmorty.presentation.ui.theme.RickAndMortyTheme

@Composable
fun LocationListRoute(
    onLocationClick: (Int) -> Unit,
) {
    val locationDb = LocationDb()
    val locations = locationDb.getAllLocations()
    LocationListScreen(
        locations = locations,
        onLocationClick = onLocationClick,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun LocationListScreen(
    locations: List<Location>,
    onLocationClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = {
            BottomNavBar(selectedItem = 1, onItemSelected = {})
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier.padding(paddingValues)
        ) {
            items(locations) { location ->
                LocationItem(
                    location = location,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onLocationClick(location.id) }
                )
            }
        }
    }
}

@Composable
private fun LocationItem(
    location: Location,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column {
            Text(text = location.name, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = location.type,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLocationListScreen() {
    RickAndMortyTheme {
        Surface {
            val db = LocationDb()
            LocationListScreen(
                locations = db.getAllLocations().take(6),
                onLocationClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
