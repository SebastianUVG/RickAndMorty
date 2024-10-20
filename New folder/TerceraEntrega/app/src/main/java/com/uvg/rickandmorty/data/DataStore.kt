package com.uvg.rickandmorty.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Define la clave para el nombre del usuario
val nameKey = stringPreferencesKey("name")

// Función para guardar el nombre en DataStore
suspend fun saveNameToDataStore(dataStore: DataStore<Preferences>, name: String) {
    dataStore.edit { preferences ->
        preferences[nameKey] = name
    }
}

// Función para leer el nombre desde DataStore
fun getNameFromDataStore(dataStore: DataStore<Preferences>): Flow<String?> {
    return dataStore.data.map { preferences ->
        preferences[nameKey] ?: ""
    }
}

