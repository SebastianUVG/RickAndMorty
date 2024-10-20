package com.uvg.rickandmorty.presentation.mainFlow.location.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.rickandmorty.data.source.LocationDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocationListViewModel : ViewModel() {
    private val locationDb = LocationDb()
    private val _uiState: MutableStateFlow<LocationListState> = MutableStateFlow(LocationListState())
    val uiState = _uiState.asStateFlow()

    fun getLocations() {
        viewModelScope.launch {
            _uiState.value = LocationListState(loading = true)

            delay(5000) // Simula un tiempo de carga

            try {
                val locations = locationDb.getAllLocations()
                _uiState.value = LocationListState(data = locations, loading = false, hasError = false)
            } catch (e: Exception) {
                _uiState.value = LocationListState(loading = false, hasError = true)
            }
        }
    }

    fun retryLoading() {
        if (_uiState.value.hasError) {
            getLocations()
        }
    }

    fun triggerError() {
        _uiState.value = LocationListState(loading = false, hasError = true)
    }
}
