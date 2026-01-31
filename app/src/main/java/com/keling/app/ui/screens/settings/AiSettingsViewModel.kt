package com.keling.app.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keling.app.data.preferences.AiPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AiSettingsUiState(
    val apiKey: String = "",
    val saveSuccess: Boolean = false
)

@HiltViewModel
class AiSettingsViewModel @Inject constructor(
    private val preferencesRepository: AiPreferencesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AiSettingsUiState())
    val uiState: StateFlow<AiSettingsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            preferencesRepository.apiKey
                .catch { }
                .collect { key ->
                    _uiState.update { it.copy(apiKey = key, saveSuccess = false) }
                }
        }
    }

    fun saveApiKey(value: String) {
        viewModelScope.launch {
            preferencesRepository.setApiKey(value.trim())
            _uiState.update { it.copy(saveSuccess = true) }
        }
    }

    fun clearSaveSuccess() {
        _uiState.update { it.copy(saveSuccess = false) }
    }
}
