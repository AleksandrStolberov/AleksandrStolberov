package com.example.pix.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pix.data.flickr.FlickrRepository
import com.example.pix.domain.entity.Picture
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: FlickrRepository) : ViewModel() {


    private val _pictures = MutableStateFlow<List<Picture>>(emptyList())

    val pictures: StateFlow<List<Picture>>
        get() = _pictures.asStateFlow()

    fun getPictures() {
        viewModelScope.launch {
            val result = repository.search()
            result.fold(
                onSuccess = {
                    _pictures.value = it
                },
                onFailure = {
                    Log.d("MainViewModel", "Error in result: ${it.message}")
                }
            )
        }

    }

}