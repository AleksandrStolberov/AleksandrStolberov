package com.example.composeapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.data.BinRepository
import com.example.composeapp.data.model.Card
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: BinRepository) : ViewModel() {

    private val _card = MutableStateFlow<List<Card>>(emptyList())
    val card = _card.asStateFlow()
    
    fun getInfo(bin: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getCardInfo(bin)
            }.fold(
                onSuccess = {
                    _card.value = listOf(it)
                },
                onFailure = {  }
            )
        }

    }

}