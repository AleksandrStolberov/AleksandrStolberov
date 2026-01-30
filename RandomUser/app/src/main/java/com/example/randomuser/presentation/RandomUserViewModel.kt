package com.example.randomuser.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.GetRandomUserUseCase
import com.example.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomUserViewModel @Inject constructor(
    private val useCase: GetRandomUserUseCase
) : ViewModel() {

    private val _user = MutableStateFlow<List<User>>(emptyList())
    val user = _user.asStateFlow()

    private val _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()


    fun getRandomUser(gender: String, nat: String) {
        viewModelScope.launch {
            _state.value = State.Loading
            runCatching {
                useCase.getRandomUser(gender, nat)
            }.fold(
                onSuccess = {
                    _user.value = user.value + it
                    _state.value = State.Success
                }, onFailure = {
                    _state.value = State.Error(it.message ?: "No information")
                }
            )
        }
    }

    fun deleteUser(user: User) {
        val newList = _user.value.toMutableList()
        newList.remove(user)
        _user.value = newList
    }

}