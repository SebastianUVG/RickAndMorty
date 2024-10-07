package com.uvg.rickandmorty.presentation.mainFlow.character.profile

import com.uvg.rickandmorty.data.model.Character

data class CharacterProfileState(
    val data: Character? = null,
    val loading: Boolean = false,
    val hasError: Boolean = false // Nueva propiedad para indicar si hubo error
)
