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

data class FriendItem(
    val id: String,
    val name: String,
    val avatarLetter: String,
    val status: String,
    val lastActive: String,
    val studyHours: Int,
    val completedTasks: Int,
    val level: Int
)

data class FriendsUiState(
    val friends: List<FriendItem> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = ""
)

@HiltViewModel
class FriendsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(FriendsUiState(isLoading = true))
    val uiState: StateFlow<FriendsUiState> = _uiState.asStateFlow()

    init {
        loadFriends()
    }

    private fun loadFriends() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val list = listOf(
                FriendItem("f1", "小明", "明", "在线学习", "刚刚", 42, 128, 10),
                FriendItem("f2", "小红", "红", "离线", "2小时前", 38, 95, 9),
                FriendItem("f3", "小刚", "刚", "在线", "5分钟前", 56, 156, 12),
                FriendItem("f4", "小丽", "丽", "离线", "昨天", 29, 72, 8),
                FriendItem("f5", "小华", "华", "在线学习", "刚刚", 45, 112, 11)
            )
            _uiState.update {
                it.copy(friends = list, isLoading = false)
            }
        }
    }

    fun setSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun filteredFriends(): List<FriendItem> {
        val q = _uiState.value.searchQuery.trim().lowercase()
        return if (q.isEmpty()) _uiState.value.friends
        else _uiState.value.friends.filter { it.name.contains(q) }
    }
}
