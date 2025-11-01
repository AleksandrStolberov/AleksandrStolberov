package com.example.rickandmorty.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.domain.GetCharactersByNameUseCase
import com.example.rickandmorty.domain.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getCharactersUseCase: GetCharactersUseCase,
    val getCharactersByNameUseCase: GetCharactersByNameUseCase
) : ViewModel() {

    val items = getCharactersUseCase.invoke().cachedIn(viewModelScope)

    private val _searched = MutableStateFlow(emptyList<Character>())
    val searched = _searched.asStateFlow()

    var searchQuery by mutableStateOf("")
        private set

    var isSearchActive by mutableStateOf(false)
        private set

    fun performSearch(name: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                getCharactersByNameUseCase.invoke(name)
            }.fold(
                onSuccess = {
                    _searched.value = it
                },
                onFailure = {}
            )
        }
    }

    fun onSearchQueryChange(query: String) {
        searchQuery = query

        if (query.length > 2) {
            performSearch(query)
        } else {
            _searched.value = emptyList()
        }
    }

    fun onSearchActiveChange(active: Boolean) {
        isSearchActive = active
        if (!active) {
            // Сбрасываем поиск при закрытии
            _searched.value = emptyList()
        }
    }

    fun onSearch() {
        if (searchQuery.isNotEmpty()) {
            performSearch(searchQuery)
        }
    }

    fun clearSearch() {
        searchQuery = ""
        _searched.value = emptyList()
    }

}