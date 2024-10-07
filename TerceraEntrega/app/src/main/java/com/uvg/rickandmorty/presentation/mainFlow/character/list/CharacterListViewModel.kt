package com.uvg.rickandmorty.presentation.mainFlow.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.rickandmorty.data.source.CharacterDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterListViewModel : ViewModel() {
    private val characterDb = CharacterDb()
    private val _uiState: MutableStateFlow<CharacterListState> = MutableStateFlow(CharacterListState())
    val uiState = _uiState.asStateFlow()

    fun getCharacters() {
        viewModelScope.launch {
            _uiState.value = CharacterListState(loading = true)

            delay(5000) // Simula un tiempo de carga

            try {
                val characters = characterDb.getAllCharacters()
                _uiState.value = CharacterListState(data = characters, loading = false, hasError = false)
            } catch (e: Exception) {
                _uiState.value = CharacterListState(loading = false, hasError = true)
            }
        }
    }

    fun retryLoading() {
        if (_uiState.value.hasError) {
            getCharacters()
        }
    }

    fun triggerError() {
        _uiState.value = CharacterListState(loading = false, hasError = true)
    }
}
