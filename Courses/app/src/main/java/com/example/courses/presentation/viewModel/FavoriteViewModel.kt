package com.example.courses.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Course
import com.example.domain.GetFavoriteCoursesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val useCase: GetFavoriteCoursesUseCase
) : ViewModel() {

    private val _courses = MutableStateFlow(emptyList<Course>())
    val courses = _courses.asStateFlow()

    fun getFavoriteCourse() {
        viewModelScope.launch {
            runCatching {
                useCase.invoke()
            }.fold(
                onSuccess = {
                    _courses.value = it
                },
                onFailure = {
                    Log.e("FavoriteViewModel", it.message.toString())
                }
            )

        }
    }

    fun insertFavoriteCourse(course: Course) {
        viewModelScope.launch {
            useCase.insertFavoriteCourse(course)
        }

    }

    fun deleteFavoriteCourse(id: Long) {
        viewModelScope.launch {
            useCase.deleteFavoriteCourse(id)
        }
    }


}