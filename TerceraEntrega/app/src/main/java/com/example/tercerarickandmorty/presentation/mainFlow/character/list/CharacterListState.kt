package com.uvg.rickandmorty.presentation.mainFlow.character.list

import com.uvg.rickandmorty.data.model.Character

data class CharacterListState(
    val data: List<Character>? = null,
    val loading: Boolean = false,
    val hasError: Boolean = false
)
