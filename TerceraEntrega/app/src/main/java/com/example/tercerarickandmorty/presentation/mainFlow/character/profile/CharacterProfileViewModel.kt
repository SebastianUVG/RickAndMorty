package com.uvg.rickandmorty.presentation.mainFlow.character.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.rickandmorty.data.model.Character
import com.uvg.rickandmorty.data.source.CharacterDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterProfileViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val characterDb = CharacterDb()
    private val _uiState: MutableStateFlow<CharacterProfileState> = MutableStateFlow(CharacterProfileState())
    val uiState = _uiState.asStateFlow()

    fun getCharacterData(characterId: Int) {
        // Verificar si hay un error y detener la carga
        if (_uiState.value.hasError) return

        viewModelScope.launch {
            _uiState.update { state -> state.copy(loading = true, hasError = false) }

            delay(2000) // Simular tiempo de carga

            try {
                val character = characterDb.getCharacterById(characterId)
                _uiState.update { state -> state.copy(data = character, loading = false, hasError = false) }
            } catch (e: Exception) {
                _uiState.update { state -> state.copy(loading = false, hasError = true) }
            }
        }
    }

    fun retryLoading() {
        // Solo se permite volver a cargar si hay un error
        if (_uiState.value.hasError) {
            getCharacterData(_uiState.value.data?.id ?: return) // Llama a la carga de datos
        }
    }

    fun triggerError() {
        // Cambia el estado a error al hacer clic en la pantalla de carga
        _uiState.update { state -> state.copy(loading = false, hasError = true) }
    }
}
