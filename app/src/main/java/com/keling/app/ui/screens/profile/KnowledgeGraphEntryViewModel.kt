package com.keling.app.ui.screens.profile

import androidx.lifecycle.ViewModel
import com.keling.app.data.model.Course
import androidx.lifecycle.viewModelScope
import com.keling.app.data.model.Course
import com.keling.app.data.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class KnowledgeGraphEntryUiState(
    val courses: List<Course> = emptyList(),
    val isLoading: Boolean = true
)

@HiltViewModel
class KnowledgeGraphEntryViewModel @Inject constructor(
    private val courseRepository: CourseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(KnowledgeGraphEntryUiState(isLoading = true))
    val uiState: StateFlow<KnowledgeGraphEntryUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            courseRepository.getAllCourses().collect { courses ->
                _uiState.update {
                    it.copy(courses = courses, isLoading = false)
                }
            }
        }
    }
}
