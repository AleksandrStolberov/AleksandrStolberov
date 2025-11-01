package com.example.courses.presentation

sealed class State {

    object Loading : State()
    object Success : State()
    data class Error(val message: String) : State()

}