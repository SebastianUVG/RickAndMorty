package com.uvg.rickandmorty.presentation.mainFlow.location.list

import com.uvg.rickandmorty.data.model.Location

data class LocationListState(
    val data: List<Location>? = null,
    val loading: Boolean = false,
    val hasError: Boolean = false
)
