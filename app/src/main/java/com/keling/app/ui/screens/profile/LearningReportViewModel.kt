package com.keling.app.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keling.app.data.model.Course
import com.keling.app.data.model.KnowledgePoint
import com.keling.app.data.model.LearningReport
import com.keling.app.data.remote.KelingApiService
import com.keling.app.data.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LearningReportUiState(
    val courses: List<Course> = emptyList(),
    val selectedCourseId: String? = null,
    val report: LearningReport? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class LearningReportViewModel @Inject constructor(
    private val kelingApiService: KelingApiService,
    private val courseRepository: CourseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LearningReportUiState(isLoading = true))
    val uiState: StateFlow<LearningReportUiState> = _uiState.asStateFlow()

    init {
        loadCourses()
    }

    private fun loadCourses() {
        viewModelScope.launch {
            courseRepository.getAllCourses().collect { courses ->
                val selectedId = _uiState.value.selectedCourseId ?: courses.firstOrNull()?.id
                _uiState.update {
                    it.copy(
                        courses = courses,
                        selectedCourseId = selectedId,
                        isLoading = it.report == null && courses.isNotEmpty()
                    )
                }
                if (selectedId != null) {
                    loadReport(selectedId)
                } else {
                    val fallback = createFallbackReport("all", "全部学习")
                    _uiState.update { it.copy(report = fallback, isLoading = false) }
                }
            }
        }
    }

    fun selectCourse(courseId: String) {
        _uiState.update { it.copy(selectedCourseId = courseId, isLoading = true, error = null) }
        loadReport(courseId)
    }

    fun loadReport(courseId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val response = try {
                    kelingApiService.getLearningReport(token = "", courseId = courseId)
                } catch (_: Exception) {
                    null
                }
                if (response != null && response.isSuccessful && response.body() != null) {
                    _uiState.update {
                        it.copy(
                            report = response.body()!!.report,
                            isLoading = false,
                            error = null
                        )
                    }
                } else {
                    val course = courseRepository.getCourseById(courseId)
                    val fallback = createFallbackReport(courseId, course?.name ?: courseId)
                    _uiState.update {
                        it.copy(report = fallback, isLoading = false, error = null)
                    }
                }
            } catch (e: Exception) {
                val course = courseRepository.getCourseById(courseId)
                val fallback = createFallbackReport(courseId, course?.name ?: courseId)
                _uiState.update {
                    it.copy(report = fallback, isLoading = false, error = null)
                }
            }
        }
    }

    private fun createFallbackReport(courseId: String, courseName: String): LearningReport {
        val baseId = courseName.ifBlank { "course" }
        return LearningReport(
            userId = "local",
            courseId = courseId,
            accuracy = 0.78f,
            speed = 3.2f,
            knowledgeGaps = listOf(
                KnowledgePoint("${baseId}_gap1", "薄弱点A", "需加强练习", courseId, difficulty = 3, importance = 4, masteryLevel = 0.4f),
                KnowledgePoint("${baseId}_gap2", "薄弱点B", "建议复习", courseId, difficulty = 4, importance = 5, masteryLevel = 0.35f)
            ),
            strongPoints = listOf(
                KnowledgePoint("${baseId}_strong1", "掌握较好A", null, courseId, difficulty = 2, importance = 4, masteryLevel = 0.85f),
                KnowledgePoint("${baseId}_strong2", "掌握较好B", null, courseId, difficulty = 2, importance = 3, masteryLevel = 0.9f)
            ),
            suggestions = listOf(
                "建议每天保持 30 分钟练习，巩固薄弱知识点。",
                "可优先完成「薄弱点A」相关题目，再拓展「薄弱点B」。",
                "本周可设定小目标：正确率提升至 85% 以上。"
            )
        )
    }
}
