package com.keling.app.ui.screens.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keling.app.data.model.TaskStatus
import com.keling.app.data.model.TaskType
import com.keling.app.data.model.Task
import com.keling.app.data.model.TaskRecord
import com.keling.app.data.repository.TaskRecordRepository
import com.keling.app.data.repository.TaskRepository
import com.keling.app.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

data class TasksUiState(
    val allTasks: List<Task> = emptyList(),
    val filteredTasks: List<Task> = emptyList(),
    val totalTasks: Int = 0,
    val completedTasks: Int = 0,
    val inProgressTasks: Int = 0,
    val isLoading: Boolean = false,
    val grade: String = "2024"
)

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val taskRecordRepository: TaskRecordRepository, // 注入记录仓库
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TasksUiState())
    val uiState: StateFlow<TasksUiState> = _uiState.asStateFlow()

    private var loadJob: Job? = null

    init {
        loadTasks()
    }

    private fun loadTasks() {
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            // 使用 .value 赋值代替 update 扩展，避免扩展可见性/版本问题
            _uiState.value = _uiState.value.copy(isLoading = true)
            val grade = userRepository.getCurrentUser().first()?.grade ?: "2024"
            _uiState.value = _uiState.value.copy(grade = grade)
            val existing = taskRepository.getTasksForGrade(grade).first()
            if (existing.isEmpty()) {
                taskRepository.generateAndSaveTasksForGrade(grade)
            }
            taskRepository.getTasksForGrade(grade).collect { list ->
                _uiState.value = _uiState.value.copy(
                    allTasks = list,
                    filteredTasks = list,
                    totalTasks = list.size,
                    completedTasks = list.count { t -> t.status == TaskStatus.COMPLETED },
                    inProgressTasks = list.count { t -> t.status == TaskStatus.IN_PROGRESS },
                    isLoading = false
                )
            }
        }
    }

    fun filterByType(type: TaskType?) {
        val allTasks = _uiState.value.allTasks
        val filtered = if (type == null) allTasks else allTasks.filter { it.type == type }
        _uiState.value = _uiState.value.copy(filteredTasks = filtered)
    }

    fun refresh() {
        loadTasks()
    }

    fun completeTask(task: Task) {
        viewModelScope.launch {
            // 1. 更新任务本身的完成状态（请确认 TaskRepository 的实际方法名）
            // 常见 repos 使用 update(task: Task)
            // 如果你的 repository 提供的是 updateTask(task) 或其它名字，请替换为正确的方法名
            taskRepository.update(task.copy(isCompleted = true, status = TaskStatus.COMPLETED))

            // 2. 写入学习记录（构造 TaskRecord）
            val record = TaskRecord(
                taskId = task.id,
                taskTitle = task.title,
                completeTime = LocalDateTime.now(),
                taskType = task.type?.name,
                duration = task.estimatedDuration ?: 0
            )

            // 调用你的记录仓库的方法 — 常见名 addTaskRecord / insertTaskRecord 等
            // 我这里使用 addTaskRecord，若你的 repository 方法名不同请替换：
            taskRecordRepository.addTaskRecord(record)
        }
    }
}