package com.uvg.rickandmorty.presentation.mainFlow.profile

import android.content.res.Configuration
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey

import com.uvg.rickandmorty.presentation.ui.theme.RickAndMortyTheme

@Composable
fun ProfileRoute(
    onLogOutClick: () -> Unit
) {
    ProfileScreen(
        onLogOutClick = onLogOutClick,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun ProfileScreen(
    onLogOutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer, shape = CircleShape)
        ) {
            Icon(
                Icons.Outlined.Person,
                contentDescription = "Person",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Nombre:")
            Text(text = "Sebastian Garcia")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Carné:")
            Text(text = "22291")
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(onClick = onLogOutClick) {
            Text("Cerrar sesión")
        }
    }
}
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewProfileScreen() {
    RickAndMortyTheme() {
        Surface {
            ProfileScreen(
                onLogOutClick = { /*TODO*/ },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
