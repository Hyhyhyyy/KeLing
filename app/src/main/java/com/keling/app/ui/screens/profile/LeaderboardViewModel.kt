package com.keling.app.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LeaderboardUser(
    val id: String,
    val rank: Int,
    val name: String,
    val avatarLetter: String,
    val level: Int,
    val exp: Int,
    val studyHours: Int,
    val completedTasks: Int,
    val isCurrentUser: Boolean = false
)

data class LeaderboardUiState(
    val weekly: List<LeaderboardUser> = emptyList(),
    val total: List<LeaderboardUser> = emptyList(),
    val selectedTab: Int = 0,
    val isLoading: Boolean = false
)

@HiltViewModel
class LeaderboardViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(LeaderboardUiState(isLoading = true))
    val uiState: StateFlow<LeaderboardUiState> = _uiState.asStateFlow()

    init {
        loadLeaderboard()
    }

    private fun loadLeaderboard() {
        viewModelScope.launch {
            val weekly = listOf(
                LeaderboardUser("u1", 1, "学习达人", "学", 12, 3200, 48, 156, true),
                LeaderboardUser("u2", 2, "小明", "明", 11, 2980, 45, 142, false),
                LeaderboardUser("u3", 3, "小刚", "刚", 10, 2750, 42, 128, false),
                LeaderboardUser("u4", 4, "小红", "红", 10, 2600, 40, 118, false),
                LeaderboardUser("u5", 5, "小丽", "丽", 9, 2450, 38, 105, false),
                LeaderboardUser("u6", 6, "小华", "华", 9, 2300, 36, 98, false),
                LeaderboardUser("u7", 7, "小强", "强", 8, 2150, 34, 92, false),
                LeaderboardUser("u8", 8, "小美", "美", 8, 2000, 32, 85, false)
            )
            val total = listOf(
                LeaderboardUser("u2", 1, "小明", "明", 15, 5200, 120, 280, false),
                LeaderboardUser("u3", 2, "小刚", "刚", 14, 4850, 115, 265, false),
                LeaderboardUser("u1", 3, "学习达人", "学", 12, 3200, 48, 156, true),
                LeaderboardUser("u4", 4, "小红", "红", 11, 2980, 95, 210, false),
                LeaderboardUser("u5", 5, "小丽", "丽", 10, 2750, 88, 195, false),
                LeaderboardUser("u6", 6, "小华", "华", 10, 2600, 82, 185, false),
                LeaderboardUser("u7", 7, "小强", "强", 9, 2450, 76, 172, false),
                LeaderboardUser("u8", 8, "小美", "美", 9, 2300, 70, 165, false)
            )
            _uiState.update {
                it.copy(weekly = weekly, total = total, isLoading = false)
            }
        }
    }

    fun setSelectedTab(index: Int) {
        _uiState.update { it.copy(selectedTab = index) }
    }
}
