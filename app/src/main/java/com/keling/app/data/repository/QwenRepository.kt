package com.keling.app.data.repository

import com.keling.app.BuildConfig
import com.keling.app.data.network.QwenApiClient
import com.keling.app.data.network.QwenRequest
import com.keling.app.data.preferences.AiPreferencesRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QwenRepository @Inject constructor(
    private val qwenApiClient: QwenApiClient,
    private val aiPreferencesRepository: AiPreferencesRepository
) {
    suspend fun chat(messages: List<QwenRequest.Message>): String {
        val storedKey = aiPreferencesRepository.apiKey.first().trim()
        val resolvedKey = if (storedKey.isNotEmpty()) storedKey else BuildConfig.QWEN_API_KEY.trim()
        if (resolvedKey.isBlank()) {
            return "Error: QWEN_API_KEY is empty"
        }
        val apiKey = "Bearer $resolvedKey"
        return try {
            val request = QwenRequest(
                input = QwenRequest.Input(
                    messages = messages
                )
            )

            val response = qwenApiClient.api.chat(apiKey, request)
            if (response.isSuccessful) {
                response.body()?.output?.choices?.firstOrNull()?.message?.content ?: ""
            } else {
                "Error: ${response.code()} ${response.message()}"
            }
        } catch (e: Exception) {
            "Exception: ${e.message}"
        }
    }
}
