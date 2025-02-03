package com.example.composeapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.data.BinRepository
import com.example.composeapp.data.model.Card
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CacheViewModel @Inject constructor(private val repository: BinRepository) : ViewModel() {

    suspend fun getAllCards(): StateFlow<List<Card>> {
        return repository.getAllCards().stateIn(viewModelScope)
    }

    suspend fun deleteAllCards() {
        repository.deleteAll()
    }
}