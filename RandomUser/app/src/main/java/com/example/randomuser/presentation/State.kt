package com.example.randomuser.presentation

sealed class State {

    object Loading : State()
    object Success : State()
    data class Error(val message: String) : State()

}