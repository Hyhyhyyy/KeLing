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
    val providerId: String = "qwen",
    val modelId: String = "qwen-turbo",
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
            // Combine flows would be better but simple launch is fine
            launch {
                preferencesRepository.apiKey.collect { key ->
                    _uiState.update { it.copy(apiKey = key) }
                }
            }
            launch {
                preferencesRepository.selectedProviderId.collect { pid ->
                    _uiState.update { it.copy(providerId = pid) }
                }
            }
            launch {
                preferencesRepository.selectedModelId.collect { mid ->
                    _uiState.update { it.copy(modelId = mid) }
                }
            }
        }
    }

    fun saveApiKey(value: String) {
        viewModelScope.launch {
            preferencesRepository.setApiKey(value.trim())
            _uiState.update { it.copy(saveSuccess = true) }
        }
    }

    fun updateConfig(providerId: String, modelId: String) {
        viewModelScope.launch {
            preferencesRepository.setAiConfig(providerId, modelId)
            // No saveSuccess toast needed for dropdown change usually, but can add if needed
        }
    }

    fun clearSaveSuccess() {
        _uiState.update { it.copy(saveSuccess = false) }
    }
}
