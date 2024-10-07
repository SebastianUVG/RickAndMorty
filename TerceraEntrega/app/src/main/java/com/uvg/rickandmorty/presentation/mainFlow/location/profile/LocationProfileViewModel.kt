package com.uvg.rickandmorty.presentation.mainFlow.location.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.uvg.rickandmorty.data.model.Location
import com.uvg.rickandmorty.data.source.LocationDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationProfileViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val locationDb = LocationDb()
    private val locationProfile = savedStateHandle.toRoute<LocationProfileDestination>()
    private val _uiState: MutableStateFlow<LocationProfileState> = MutableStateFlow(LocationProfileState())
    val uiState = _uiState.asStateFlow()

    fun getLocationData() {
        // Verificar si hay un error y detener la carga
        if (_uiState.value.hasError) return

        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    loading = true,
                    hasError = false // Reiniciar el estado de error
                )
            }

            delay(2000) // Simular tiempo de carga

            try {
                val location = locationDb.getLocationById(locationProfile.locationId)

                _uiState.update { state ->
                    state.copy(
                        data = location,
                        loading = false,
                        hasError = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { state ->
                    state.copy(
                        loading = false,
                        hasError = true // Marcar error si falla la carga
                    )
                }
            }
        }
    }


    fun retryLoading() {
        // Solo se permite volver a cargar si hay un error
        if (_uiState.value.hasError) {
            getLocationData() // Llama a la carga de datos
        }
    }

    fun triggerError() {
        // Cambia el estado a error al hacer clic en la pantalla de carga
        _uiState.update { state ->
            state.copy(
                loading = false, // Detener carga
                hasError = true   // Marcar error
            )
        }
    }
}

