package com.example.courses.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courses.presentation.State
import com.example.domain.Course
import com.example.domain.GetCoursesUseCase
import com.example.domain.GetFavoriteCoursesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetCoursesUseCase,
    private val favoriteCourseUseCase: GetFavoriteCoursesUseCase
) : ViewModel() {

    private val _courses = MutableStateFlow(emptyList<Course>())
    val courses = _courses.asStateFlow()

    private val _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()

    fun getCourses() {
        viewModelScope.launch {
            _state.value = State.Loading
            runCatching {
                useCase.invoke()
            }.fold(
                onSuccess = {
                    _courses.value = it
                    _state.value = State.Success
                },
                onFailure = {
                    _state.value = State.Error(it.message ?: "No information")
                }
            )
        }
    }

    fun insertFavoriteCourse(course: Course) {
        viewModelScope.launch {
            favoriteCourseUseCase.insertFavoriteCourse(course)
        }

    }

    fun deleteFavoriteCourse(id: Long) {
        viewModelScope.launch {
            favoriteCourseUseCase.deleteFavoriteCourse(id)
        }
    }
}