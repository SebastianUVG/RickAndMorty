package com.uvg.rickandmorty.presentation.character.profile

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.uvg.rickandmorty.presentation.login.LoginDestination

import com.uvg.rickandmorty.presentation.navigation.BottomNavBar
import com.uvg.rickandmorty.presentation.ui.theme.RickAndMortyTheme




@Composable
fun ProfileRoute(
    onLoginClick: () -> Unit,
) {
    ProfileScreen(
        onLoginClick = onLoginClick,
        modifier = Modifier.fillMaxSize()
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
){
    Scaffold(
        bottomBar = {
            BottomNavBar(
                selectedItem = 2, // Indica que el item "Profile" está seleccionado
                onItemSelected = {}
            )
        },
        content = { paddingValues ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 64.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .background(MaterialTheme.colorScheme.secondaryContainer, shape = CircleShape)
                ) {
                    Icon(
                        Icons.Outlined.Person,
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                ProfilePropItem(
                    title = "Nombre:",
                    value = "Sebastian Garcia",
                    modifier = Modifier.fillMaxWidth()
                )
                ProfilePropItem(
                    title = "Carnet:",
                    value = "22291",
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = onLoginClick,
                    modifier = Modifier.fillMaxWidth()
                ) { // Llama a la función para ir a la pagina de login.
                    Text(text = "Cerrar sesión")
                }
            }
        }
    )
}

@Composable
private fun ProfilePropItem(
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

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewProfileScreen() {
    RickAndMortyTheme {
        ProfileScreen(onLoginClick = { /*TODO*/ },
            modifier = Modifier.fillMaxSize())
    }
}
